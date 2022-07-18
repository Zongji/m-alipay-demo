package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.utils.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

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
        orderDTO.setTotalAmount(productDTO.getPrice());
        orderDTO.setPrice(productDTO.getPrice());
        orderDTO.setOrderStatus("NEW");
        orderDTO.setSubject("商品购买");
        orderDTO.setTradeNo(IdUtils.getSeq("O"));
        int i = orderMapper.insert(orderDTO);
        return orderDTO;
    }

    public List<OrderDTO> queryOrders(String status, int page, int pageSize) {
        Page<OrderDTO> page1 = new Page(page, pageSize);
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        if (!StringUtils.isEmpty(status)){
            queryWrapper.eq("order_status", status);
        }
        page1 = orderMapper.selectPage(page1, queryWrapper);
        return page1.getRecords();
    }
}
