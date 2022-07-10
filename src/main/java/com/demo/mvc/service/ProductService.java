package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductDTO getProductByCode(String code) {
        QueryWrapper<ProductDTO> queryWrapper = new QueryWrapper<>();
        ProductDTO dto = productMapper.selectOne(queryWrapper);
        Assert.isNull(dto, "product is null");
        return dto;
    }

    public ProductDTO saveProduct() {
        return null;
    }
}
