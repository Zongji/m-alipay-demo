package com.demo.mvc.controller;

import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.PageResponseVo;
import com.demo.mvc.dto.ResponseVo;
import com.demo.mvc.service.OrderService;
import com.demo.mvc.vo.OrderRespVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @RequestMapping("/list")
    public PageResponseVo orderList(@RequestParam(value = "status", required = false) String status, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<OrderDTO> list = orderService.queryOrders(page, pageSize, status);
        PageResponseVo vo = new PageResponseVo();
        vo.setList(list);
        vo.setPage(page);
        vo.setPageSize(pageSize);
        return vo;
    }

    @RequestMapping("/detail")
    public ResponseVo orderDetail(@RequestParam("orderId") long orderId) {
        OrderRespVo orderRespVo = orderService.queryOrderDetail(orderId);
        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(orderRespVo);
        return responseVo;
    }


}
