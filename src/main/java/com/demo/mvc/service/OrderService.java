package com.demo.mvc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mvc.dto.OrderDTO;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.UserDTO;
import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.mapper.ProductMapper;
import com.demo.mvc.utils.IdUtils;
import com.demo.mvc.vo.OrderRespVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;

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

    public List<OrderDTO> queryOrders(int page, int pageSize,String status) {
        Page<OrderDTO> page1 = new Page(page, pageSize);
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        if (!StringUtils.isEmpty(status)){
            queryWrapper.eq("order_status", status);
        }
        page1 = orderMapper.selectPage(page1, queryWrapper);
        return page1.getRecords();
    }

    public OrderRespVo queryOrderDetail(long orderId) {
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        OrderDTO orderDTO = orderMapper.selectOne(queryWrapper);
        Assert.notNull(orderDTO, "order info not found");

        QueryWrapper<ProductDTO> productDTOQueryWrapper = new QueryWrapper<>();
        productDTOQueryWrapper.eq("id", orderDTO.getProductId());
        ProductDTO productDTO = productMapper.selectOne(productDTOQueryWrapper);
        Assert.notNull(productDTO, "product not found with id:" + orderDTO.getProductId());

        OrderRespVo orderRespVo = new OrderRespVo();
        BeanUtils.copyProperties(orderDTO, orderRespVo);
        orderRespVo.setProductDTO(productDTO);
        return orderRespVo;
    }

    public OrderDTO queryOrderByTradeNo(String tradeNo) {
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("trade_no", tradeNo);
        OrderDTO orderDTO = orderMapper.selectOne(queryWrapper);
        Assert.notNull(orderDTO, "order info not found");
        return orderDTO;
    }

    public void updateOrder(OrderDTO update) {
        orderMapper.updateById(update);
    }

    public List<OrderDTO> queryOrderByStatus(String status, int batchSize) {
        Page<OrderDTO> page1 = new Page(1, batchSize);
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", status);
        page1 = orderMapper.selectPage(page1, queryWrapper);
        return page1.getRecords();
    }
}
