package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.ProductMapper;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@MapperScan("com.demo.mvc.mapper")
public class OrderServiceTest extends TestCase {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductMapper productMapper;

    public void testAddOrder() {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1L);

        QueryWrapper<ProductDTO> productDTOQueryWrapper = new QueryWrapper<>();
        productDTOQueryWrapper.eq("productCode", "A0001");
        ProductDTO productDTO = productMapper.selectOne(productDTOQueryWrapper);

        orderService.addOrder(userDTO, productDTO);
    }
}