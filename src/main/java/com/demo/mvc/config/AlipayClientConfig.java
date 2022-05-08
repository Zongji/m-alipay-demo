package com.demo.mvc.config;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayClientConfig {
    @Bean
    public AlipayClient getAlipayClient() {
        AlipayClient client = new DefaultAlipayClient(AlipayConfig.URL,
                AlipayConfig.APPID, AlipayConfig.RSA_PRIVATE_KEY,
                AlipayConfig.FORMAT, AlipayConfig.CHARSET,
                AlipayConfig.ALIPAY_PUBLIC_KEY,AlipayConfig.SIGNTYPE);
        return client;
    }
}
