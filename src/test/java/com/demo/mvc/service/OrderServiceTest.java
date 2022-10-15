package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.demo.mvc.BaseTest;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.RefundTDO;
import com.demo.mvc.vo.ResponseVo;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.ProductMapper;
import com.demo.mvc.utils.GsonUtils;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@Slf4j
public class OrderServiceTest extends BaseTest {
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
        List<OrderDTO> list = orderService.queryOrders(1, 10, "1");
        log.info(list.toString());
    }

    @Test
    public void queryOrderByStatus() {
        List<OrderDTO> list = orderService.queryOrderByStatus(OrderStatusEnum.CREATE.name(), 3);
        log.info(GsonUtils.toJson(list));
    }


    @Test
    public void refundApply() {
        ResponseVo responseVo = orderService.refundApply(1577565660915830785L);
        log.info(GsonUtils.toJson(responseVo));
        List<RefundTDO> list = orderService.queryToRefundList();
        log.info(GsonUtils.toJson(list));
    }

}