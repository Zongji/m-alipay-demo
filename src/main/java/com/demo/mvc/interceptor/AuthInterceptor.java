package com.demo.mvc.interceptor;

import com.demo.mvc.controller.ProductController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthInterceptor implements HandlerInterceptor {
    private final static Logger LOG = LoggerFactory.getLogger(AuthInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession userSession = request.getSession();

        //为/login请求放行
        if ((request.getRequestURL()).toString().contains("login")){
            return true;
        }

        //如果请求用户的属性，则跳转到登录界面，并阻止通过
        if(userSession.getAttribute("user") == null) {
            String fromURL = request.getQueryString();
            String uri = request.getRequestURL().toString();
            LOG.info(uri);
//            response.sendRedirect("/login?fromURL=" + fromURL);
            return false;
        }
        return true;
    }
}
