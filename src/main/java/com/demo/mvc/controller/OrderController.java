package com.demo.mvc.controller;

import com.demo.mvc.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Service
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/list")
    public ModelAndView orderList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("");

        return modelAndView;
    }




}
