package com.demo.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {
    private final static Logger LOG = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = {"", "/"}, method = RequestMethod.GET)
    public String home() {
        LOG.info("home==========");
        return "index";
    }

    @ResponseBody
    @GetMapping("/index")
    public String index(String num) {
        return num;
    }

    @GetMapping("index2")
    public ModelAndView index2(String num) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
