package com.demo.mvc.service;


import cn.hutool.core.date.DateUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.config.AlipayConfig;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.RefundTDO;
import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class PaymentService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private AlipayClient alipayClient;


    public OrderDTO buildOrder(HttpServletRequest request) throws UnsupportedEncodingException {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCreatedAt(new Date());
        orderDTO.setUpdatedAt(new Date());
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
        String productId = new String(request.getParameter("productId").getBytes("ISO-8859-1"),"UTF-8");

        orderDTO.setUserId(1L);
        orderDTO.setTradeNo(out_trade_no);
        orderDTO.setSubject(subject);
        orderDTO.setTotalAmount(new BigDecimal(total_amount));
        orderDTO.setOrderStatus(OrderStatusEnum.CREATE.name());
        orderDTO.setPrice(new BigDecimal(total_amount));
        orderDTO.setProductId(Long.valueOf(productId));

        return orderDTO;
    }

    public String payNotify(Map<String, String> params) throws AlipayApiException {
        boolean verify_result = AlipaySignature.rsaCheckV1(params,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.CHARSET,
                AlipayConfig.SIGNTYPE);
        if (verify_result) {
            return orderService.updateOrderStatus(params);
        }
        return "not ok";
    }



    public String payReturn(HttpServletRequest request, Map<String, String> params) throws UnsupportedEncodingException, AlipayApiException {
        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
        //商户订单号
        String out_trade_no = request.getParameter("out_trade_no").toString();
        log.info("内部交易号out_trade_no:{}", out_trade_no);

        //支付宝交易号
        String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"), "UTF-8");
        log.info("支付宝交易号 trade_no:{}", trade_no);

        //获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
        //计算得出通知验证结果
        //boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
        boolean verify_result = AlipaySignature.rsaCheckV1(params,
                AlipayConfig.ALIPAY_PUBLIC_KEY,
                AlipayConfig.CHARSET,
                AlipayConfig.SIGNTYPE);

        String retMsg = "";
        if (verify_result) {//验证成功
            //////////////////////////////////////////////////////////////////////////////////////////
            //请在这里加上商户的业务逻辑程序代码
            //该页面可做页面美工编辑
            retMsg = "支付成功！！<br />";
            //——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
        } else {
            //该页面可做页面美工编辑
            retMsg = "验证失败<br />";
        }
        log.info(retMsg);
        return retMsg;
    }

    /**
     * 请求支付宝系统查询订单
     * @param outTradeNo
     * @return
     */
    public AlipayTradeQueryResponse alipayTradeQuery(String outTradeNo) {
//        log.info("====alipayTradeQuery req:{}", GsonUtils.toJson(response));
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.URL,
//                AlipayConfig.APPID,
//                AlipayConfig.RSA_PRIVATE_KEY,
//                "json",
//                AlipayConfig.CHARSET,
//                AlipayConfig.ALIPAY_PUBLIC_KEY,
//                "RSA2");  //获得初始化的AlipayClient
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest(); //创建API对应的request类
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", outTradeNo);
        request.setBizContent(GsonUtils.toJson(params));
//        request.setBizContent("{" +
//                "    \"out_trade_no\":\"20150320010101001\"," +
//                "    \"trade_no\":\"2014112611001004680073956707\"}");  //设置业务参数
        AlipayTradeQueryResponse response = null; //通过alipayClient调用API，获得对应的response类
        try {
            response = alipayClient.execute(request);
            log.info("====alipayTradeQuery:{}", response.getBody());
            return response;
        } catch (AlipayApiException e) {
            log.error("alipayTradeQuery error!", e);
        }
        return null;
    }

    /**
     * 请求支付宝系统进行退款
     * @param orderDTO
     * @return
     */
    public AlipayTradeRefundResponse alipayTradeRefund(OrderDTO orderDTO) {
        log.info("====alipayTradeRefund req:{}", GsonUtils.toJson(orderDTO));
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();//创建API对应的request类
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", orderDTO.getTradeNo());
        params.put("out_request_no", "R" + orderDTO.getTradeNo()+"_"+ System.nanoTime());
        params.put("refund_amount", orderDTO.getTotalAmount() + "");
        log.info("api param:{}", GsonUtils.toJson(params));
        request.setBizContent(GsonUtils.toJson(params));
//       request.setBizContent("{" +
                //"\"out_trade_no\":\"20150320010101001\"," +
                //"\"trade_no\":\"2014112611001004680073956707\"," +
                //"\"out_request_no\":\"1000001\"," +
                //"\"refund_amount\":\"1.00\"}"); //设置业务参数
        AlipayTradeRefundResponse response = null; //通过alipayClient调用API，获得对应的response类
        try {
            response = alipayClient.execute(request);
            log.info("====alipayTradeQuery:{}", response.getBody());
            return response;
        } catch (AlipayApiException e) {
            log.error("alipayTradeQuery error!", e);
        }
        return null;
    }

}
