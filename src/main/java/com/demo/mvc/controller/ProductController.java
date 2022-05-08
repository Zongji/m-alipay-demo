package com.demo.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("list")
    public String list() {
        LOG.info("list==========");
        return "productList";
    }
}
