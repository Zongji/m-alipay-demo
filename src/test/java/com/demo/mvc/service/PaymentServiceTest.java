package com.demo.mvc.service;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.demo.mvc.BaseTest;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.utils.GsonUtils;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class PaymentServiceTest extends BaseTest {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private OrderService orderService;

    public void testCreateOrder() {
    }

    @Test
    public void testAlipayRefund() {
        OrderDTO orderDTO = orderService.queryOrderByTradeNo("1664951073209");
        AlipayTradeRefundResponse response = paymentService.alipayTradeRefund(orderDTO);
        log.info(GsonUtils.toJson(response));
    }

    @Test
    public void testAlipayTradeQuery() {
        AlipayTradeQueryResponse response = paymentService.alipayTradeQuery("1664955934581");
        log.info("code:{}", response.getCode());
        log.info("body:{}", response.getBody());
    }
}