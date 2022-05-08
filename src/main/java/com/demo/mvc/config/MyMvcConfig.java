package com.demo.mvc.config;

import com.demo.mvc.interceptor.AuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    private final static Logger LOG = LoggerFactory.getLogger(MyMvcConfig.class);

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LOG.info("add interceptor: AuthInterceptor");
        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
