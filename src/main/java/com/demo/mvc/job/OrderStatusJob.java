package com.demo.mvc.job;

import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 订单定时任务：
 */
@Slf4j
@Configuration
@EnableScheduling
public class OrderStatusJob {
    @Autowired
    private OrderService orderService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void someTask() {
        //...
        log.info("定时任务实行:{}", new Date());

    }
}
