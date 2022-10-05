package com.demo.mvc.service;

import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;

@Service
public class PaymentService {
    @Autowired
    private OrderMapper orderMapper;

    public void createOrder(OrderDTO newOrder) {
        int i = orderMapper.insert(newOrder);
    }


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
}
