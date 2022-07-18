package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.BaseTest;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.ProductMapper;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
public class ProductServiceTest extends BaseTest {
    @Autowired
    private ProductService productService;

    @Test
    public void getProductByCode() {
        ProductDTO productDTO = productService.getProductByCode("P1657113535414");
        Assert.assertNotNull(productDTO);
        log.info(productDTO.toString());
    }

    @Test
    public void listPage() {
        List<ProductDTO> list = productService.listPage(1,2);
        list.forEach(item->log.info(item.toString()));
        Assert.assertEquals(2, list.size());

        list = productService.listPage(2,2);
        list.forEach(item->log.info(item.toString()));
        Assert.assertEquals(2, list.size());
    }
}