package com.demo.mvc.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.config.AlipayConfig;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.TradeDTO;
import com.demo.mvc.service.OrderService;
import com.demo.mvc.service.PaymentService;
import com.demo.mvc.service.ProductService;
import com.demo.mvc.utils.GsonUtils;
import com.demo.mvc.utils.IdUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/pay")
public class PaymentController extends BaseController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayClient alipayClient;

    @Autowired
    private PaymentService paymentService;

    //TODO
    @GetMapping("/detail")
    public ModelAndView detail(String code) {

        code = StringUtils.isEmpty(code) ? "A0001" : code;
        ProductDTO productDTO = productService.getProductById(code);

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
        log.info("=====订单提交=======");
        OrderDTO orderDTO = paymentService.buildOrder(request);
        log.info("created order:{}", orderDTO);
        orderService.createOrder(orderDTO);

        // 超时时间 可空
        String timeout_express = "2m";
        // 销售产品码 必填
        String product_code = "QUICK_WAP_WAY";
        /**********************/
        // SDK 公共请求类，包含公共请求参数，以及封装了签名与验签，开发者无需关注签名与验签
        //调用RSA签名方式
//        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL,
//                AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
//                AlipayConfig.FORMAT, AlipayConfig.CHARSET,
//                AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        AlipayTradeWapPayRequest alipay_request = new AlipayTradeWapPayRequest();

        // 封装请求支付信息
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(orderDTO.getTradeNo());
        model.setSubject(orderDTO.getSubject());
        model.setTotalAmount(orderDTO.getTotalAmount().toString());
        model.setBody(orderDTO.getSubject());
        model.setTimeoutExpress(timeout_express);
        model.setProductCode(orderDTO.getProductId().toString());
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

    /**
     * 支付成功后跳转的return_url地址。
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @GetMapping("/pay-return")
    public ModelAndView payReturn(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> params = getPayResultParamsMap(request);
        log.info("=====payReturn:{}", GsonUtils.toJson(params));

        String retMsg =  paymentService.payReturn(request, params);
        log.info("=====payReturn res:{}", retMsg);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("payResult");
        modelAndView.addObject("res", retMsg);
        return modelAndView;
    }

    private Map<String, String> getPayResultParamsMap(HttpServletRequest request) {
        Map<String, String> params = new HashMap<String, String>();
        Map requestParams = request.getParameterMap();

        log.info(GsonUtils.toJson(requestParams));

        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
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
        log.info(GsonUtils.toJson(params));
        return params;
    }

    /**
     * 支付宝异步通知结果
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @PostMapping("/pay-notify")
    @ResponseBody
    public String payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Map<String, String> params = getPayResultParamsMap(request);
        log.info("=====payNotify:{}", GsonUtils.toJson(params));

        String res = paymentService.payNotify(params);
        log.info("=====payNotify res:{}", res);
        return res;
    }


}


