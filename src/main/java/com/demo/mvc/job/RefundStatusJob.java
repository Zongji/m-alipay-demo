package com.demo.mvc.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.db.sql.Order;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.common.RefundStatusEnum;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.RefundTDO;
import com.demo.mvc.service.OrderService;
import com.demo.mvc.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
@EnableScheduling
public class RefundStatusJob {
    @Autowired
    private OrderService orderService;
    @Autowired
    private PaymentService paymentService;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void refundRetry() {
        log.info("定时任务发起退款执行:{}", DateUtil.formatDate(new Date()));
        List<RefundTDO> list = orderService.queryToRefundList();
        for (RefundTDO refundTDO : list) {
            OrderDTO orderDTO = orderService.queryById(refundTDO.getOrderId());
            AlipayTradeRefundResponse response = paymentService.alipayTradeRefund(orderDTO);
            if (response.isSuccess() && response.getFundChange().equals("Y")) {
                refundSuccess(refundTDO, orderDTO);
            } else {
                refundTDO.setRefundStatus(RefundStatusEnum.PAID_FAIL.name());
                orderService.updateRefundStatus(refundTDO);
            }
        }
        log.info("定时任务发起退款执行 done!");
    }



    @Transactional
    public void refundSuccess(RefundTDO refundTDO, OrderDTO orderDTO) {
        refundTDO.setRefundStatus(RefundStatusEnum.REFUND.name());
        orderService.updateRefundStatus(refundTDO);

        orderDTO.setOrderStatus(OrderStatusEnum.REFUND.name());
        orderService.updateOrder(orderDTO);
    }
}
