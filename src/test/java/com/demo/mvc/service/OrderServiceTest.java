package com.demo.mvc.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.ProductMapper;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.demo.mvc.mapper")
@Transactional
//@Rollback
@Slf4j
public class OrderServiceTest extends TestCase {
    @Autowired
    private OrderService orderService;
    @Mock
    private ProductMapper productMapper;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(12L);
        productDTO.setProductCode("P11111");
        productDTO.setName("iphone");
        when(productMapper.selectOne(any(QueryWrapper.class))).thenReturn(productDTO);
    }

    @Test
    public void testAddOrder() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(11L);

        QueryWrapper<ProductDTO> productDTOQueryWrapper = new QueryWrapper<>();
        productDTOQueryWrapper.eq("product_code", "P1657113535414");
        ProductDTO productDTO = productMapper.selectOne(productDTOQueryWrapper);
        assertNotNull(productDTO);
        log.info(productDTO.toString());

        OrderDTO orderDTO = orderService.addOrder(userDTO, productDTO);
    }

    @Test
    public void testQueryOrders() {
        Page<OrderDTO> page = orderService.queryOrders("", 1, 10);
        log.info(page.getRecords().toString());
    }
}