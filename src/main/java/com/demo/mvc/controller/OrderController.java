package com.demo.mvc.controller;

import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.PageResponseVo;
import com.demo.mvc.service.OrderService;
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
    public PageResponseVo orderList(@RequestParam("status") String status, @RequestParam("page") int page, @RequestParam("pageSize") int pageSize) {
        List<OrderDTO> list = orderService.queryOrders(status, page, pageSize);
        PageResponseVo vo = new PageResponseVo();
        vo.setList(list);
        vo.setPage(page);
        vo.setPageSize(pageSize);
        return vo;
    }

}
