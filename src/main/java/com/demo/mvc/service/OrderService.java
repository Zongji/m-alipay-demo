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
        Assert.isNull(userDTO, "user is null");
        Assert.isNull(productDTO, "product is null");

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setProductCode(productDTO.getProductCode());
        orderDTO.setAmount(productDTO.getPrice());
        orderDTO.setSubject("商品购买");
        orderDTO.setTradeNo(IdUtils.getSeq("O"));
        orderMapper.insert(orderDTO);
        return orderDTO;
    }
}
