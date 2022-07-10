package com.demo.mvc.service;

import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    public OrderDTO addOrder(UserDTO userDTO, ProductDTO productDTO) {
        Assert.notNull(userDTO, "user is null");
        Assert.notNull(productDTO, "product is null");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUserId(userDTO.getId());
        orderDTO.setProductId(productDTO.getId());
        orderDTO.setProductCode(productDTO.getProductCode());
        orderDTO.setTotalAmount(productDTO.getPrice());
        orderDTO.setPrice(productDTO.getPrice());
        orderDTO.setOrderStatus("NEW");
        orderDTO.setSubject("商品购买");
        orderDTO.setTradeNo(IdUtils.getSeq("O"));
        int i = orderMapper.insert(orderDTO);
        return orderDTO;
    }
}
