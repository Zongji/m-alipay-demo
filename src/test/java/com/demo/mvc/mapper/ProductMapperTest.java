package com.demo.mvc.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.BaseTest;
import com.demo.mvc.dto.ProductDTO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;


@Slf4j
public class ProductMapperTest extends BaseTest {

    @Autowired
    private ProductMapper productMapper;

    @Test
    public void insert() {
        log.info("----- insert ------");
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
        System.out.println("----- selectList test ------");
        QueryWrapper<ProductDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", "iphone 12_1657113535414");
        List<ProductDTO> userList = productMapper.selectList(queryWrapper);
        Assert.assertEquals(1, userList.size());
//        userList.forEach(System.out::println);
        userList.forEach(item->log.info(item.toString()));

        queryWrapper.eq("name", "iphone 1211");
        ProductDTO qu = productMapper.selectOne(queryWrapper);
        Assert.assertNull(qu);
    }
}