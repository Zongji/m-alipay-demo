package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductMapper productMapper;

    public ProductDTO getProductById(String id) {
//        QueryWrapper<ProductDTO> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("product_code", code);
        ProductDTO dto = productMapper.selectById(id);
        return dto;
    }

    public List<ProductDTO> listPage(int page, int pageSize) {
        QueryWrapper<ProductDTO> queryWrapper = new QueryWrapper<>();

        IPage<ProductDTO> pageObj = new Page<>(page, pageSize);
        IPage<ProductDTO> res = productMapper.selectPage(pageObj, queryWrapper);
        return res.getRecords();
    }

    public ProductDTO saveProduct() {
        return null;
    }
}
