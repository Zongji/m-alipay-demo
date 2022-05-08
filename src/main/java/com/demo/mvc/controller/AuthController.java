package com.demo.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthController {
    private final static Logger LOG = LoggerFactory.getLogger(ProductController.class);

    @GetMapping("/login")
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/login")
    public RedirectView login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                              HttpServletRequest request) {
        LOG.info("userName:{}, password:{}", userName, password);
        RedirectView redirectView = new RedirectView();
        return redirectView;
    }
}
