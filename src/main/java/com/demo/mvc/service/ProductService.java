package com.demo.mvc.service;

import com.demo.mvc.dto.ProductDTO;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {

    public ProductDTO getProductByCode(String code) {
        ProductDTO dto = new ProductDTO();
        dto.setProductCode(code);
        dto.setDesrciption("测试商品，iphone");
        dto.setPrice(new BigDecimal("10.5"));
        return dto;
    }
}
