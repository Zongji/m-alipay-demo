package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.ProductMapper;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.demo.mvc.mapper")
public class ProductServiceTest extends TestCase {
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void insert() {
        System.out.println("----- selectAll method test ------");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setPrice(new BigDecimal("20.5"));
        productDTO.setProductCode("P"+System.currentTimeMillis());
        productDTO.setDescription("商品详情测试测试");
        productDTO.setName("iphone 12_" + System.currentTimeMillis());
        int res = productMapper.insert(productDTO);
        Assert.assertEquals(1, res);
    }

    @Test
    public void selectList() {
        System.out.println("----- selectAll method test ------");
        QueryWrapper<ProductDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "iphone 12_1657113535414");
        List<ProductDTO> userList = productMapper.selectList(queryWrapper);
        Assert.assertEquals(1, userList.size());
        userList.forEach(System.out::println);

        queryWrapper.eq("name", "iphone 1211");
        ProductDTO qu = productMapper.selectOne(queryWrapper);
        System.out.println(qu);
    }

    @Test
    public void getProduct() {
    }
}