package com.demo.mvc.job;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.service.OrderService;
import com.demo.mvc.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class RefundRetryJob {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refundRetry() {
        //...
        log.info("定时任务重新发起退款执行:{}", new Date());
        log.info("定时任务重新发起退款执行 done!");

    }
}
