package com.demo.mvc.job;

import com.alipay.api.response.AlipayTradeQueryResponse;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.service.OrderService;
import com.demo.mvc.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

/**
 * 订单定时任务：
 */
@Slf4j
@Configuration
@EnableScheduling
public class OrderStatusJob {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void someTask() {
        //...
        log.info("定时任务实行:{}", new Date());
        List<OrderDTO> list = orderService.queryOrderByStatus(OrderStatusEnum.CREATE.name(), 2);
        for (OrderDTO orderDTO : list) {
            AlipayTradeQueryResponse response = paymentService.alipayTradeQuery(orderDTO.getTradeNo());
            orderService.updateOrderStatusByAlipayStatus(orderDTO, response);
        }
        log.info("定时任务实行 done!");

    }
}
