package com.demo.mvc.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.demo.mvc.config.AlipayConfig;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.TradeDTO;
import com.demo.mvc.service.OrderService;
import com.demo.mvc.service.ProductService;
import com.demo.mvc.utils.IdUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Controller
@RequestMapping("/pay")
public class PaymentController extends BaseControler{
    private final static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayClient alipayClient;
    
    @GetMapping("/detail")
    public ModelAndView detail(String code) {

        code = StringUtils.isEmpty(code)? "A0001": code;
        ProductDTO productDTO = productService.getProductByCode(code);


        TradeDTO tradeDTO = new TradeDTO();
        tradeDTO.setAmount(productDTO.getPrice());
        tradeDTO.setProductCode(productDTO.getProductCode());
        tradeDTO.setSubject(productDTO.getDescription());
        tradeDTO.setTradeNo(IdUtils.getSeq("I"));

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("pay");
        modelAndView.addObject("product", productDTO);
        modelAndView.addObject("trade", tradeDTO);

        return modelAndView;
    }

    @PostMapping("/submit")
    public void submit(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, AlipayApiException {
        System.out.println("=====订单提交=======");

        // 商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = request.getParameter("WIDout_trade_no").toString();
        System.out.println("商户订单号:" + out_trade_no);

        // 订单名称，必填
        String subject = request.getParameter("WIDsubject").toString();
        System.out.println("订单名称:" + subject);
        // 付款金额，必填
        String total_amount=new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        // 商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
        // 超时时间 可空
        String timeout_express="2m";
        // 销售产品码 必填
        String product_code="QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
//        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL,
//                AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
//                AlipayConfig.FORMAT, AlipayConfig.CHARSET,
//                AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request=new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model=new AlipayTradeWapPayModel();
        model.setOutTradeNo(out_trade_no);
        model.setSubject(subject);
        model.setTotalAmount(total_amount);
        model.setBody(body);
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(product_code);
        alipay_request.setBizModel(model);
        // 设置异步通知地址
        alipay_request.setNotifyUrl(AlipayConfig.notify_url);
        // 设置同步地址
        alipay_request.setReturnUrl(AlipayConfig.return_url);

        // form表单生产
        String form = "";
        try {
            // 调用SDK生成表单
            form = alipayClient.pageExecute(alipay_request).getBody();
            response.setContentType("text/html;charset=" + AlipayConfig.CHARSET);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (AlipayApiException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @GetMapping("/pay-return")
    public ModelAndView payReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String,String> params = new HashMap<String,String>();
        Map requestParams = request.getParameterMap();
        LOG.info(requestParams.toString());

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
            params.put(name, valueStr);
        }
        LOG.info(params.toString());


        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号

        String out_trade_no = request.getParameter("out_trade_no").toString();
        LOG.info(out_trade_no);
        LOG.info("内部交易号out_trade_no:{}",out_trade_no);

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
        LOG.info("支付宝交易号 trade_no:{}",trade_no);
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.CHARSET,
                AlipayConfig.SIGNTYPE);

        String retMsg = "";
        if(verify_result){//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            retMsg = "验证成功<br />";
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        }else{
            //该页面可做页面美工编辑
            retMsg = "验证失败<br />";
        }
        LOG.info(retMsg);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payResult");
        modelAndView.addObject("res", retMsg);
        return modelAndView;
    }
}
