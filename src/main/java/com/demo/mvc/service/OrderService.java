package com.demo.mvc.service;

import cn.hutool.core.date.DateUtil;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.mvc.common.OrderStatusEnum;
import com.demo.mvc.common.RefundStatusEnum;
import com.demo.mvc.dto.*;
import com.demo.mvc.mapper.OrderMapper;
import com.demo.mvc.mapper.ProductMapper;
import com.demo.mvc.mapper.RefundMapper;
import com.demo.mvc.utils.IdUtils;
import com.demo.mvc.vo.OrderRespVo;
import com.demo.mvc.vo.ResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Slf4j

public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private RefundMapper refundMapper;

    public void createOrder(OrderDTO newOrder) {
        int i = orderMapper.insert(newOrder);
    }

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

    public OrderDTO queryById(long orderId) {
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", orderId);
        OrderDTO orderDTO = orderMapper.selectOne(queryWrapper);
        return orderDTO;
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
        update.setUpdatedAt(new Date());
        orderMapper.updateById(update);
    }


    public List<OrderDTO> queryOrderByStatus(String status, int batchSize) {
        Page<OrderDTO> page1 = new Page(1, batchSize);
        QueryWrapper<OrderDTO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("order_status", status);
        page1 = orderMapper.selectPage(page1, queryWrapper);
        return page1.getRecords();
    }
    public void updateOrderStatusByAlipayStatus(OrderDTO orderDTO, AlipayTradeQueryResponse response) {
        if (orderDTO == null || response == null) {
            log.info("params is null");
            return;
        }
        OrderDTO update = new OrderDTO();
        update.setId(orderDTO.getId());
        update.setUpdatedAt(new Date());

        //支付宝系统记录不存在
        if (response.getCode().equals("40004")) {
            log.warn("trade not found in alipay, tradeNo:{}, responseBody:{}", response.getTradeNo(), response.getBody());
            if (DateUtil.offsetMinute(orderDTO.getCreatedAt(), 2).before(new Date()))  {
                //订单过期
                update.setOrderStatus(OrderStatusEnum.CLOSE.name());
                updateOrder(update);
            }
            return;
        }

        String tradeStatus = response.getTradeStatus();
        if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
            update.setOrderStatus(OrderStatusEnum.PAID.name());
        }else {
            update.setOrderStatus(OrderStatusEnum.PAID_TIMEOUT.name());
        }
        updateOrder(update);
        log.info("update status success!, trade_no:{}", orderDTO.getTradeNo());
    }

    public String updateOrderStatus(Map<String, String> params) {
        //更新支付订单到成功
        String tradeNo = params.get("out_trade_no");
        if (StringUtils.isEmpty(tradeNo)) {
            log.warn("trade_no参数为空");
            return "not ok";
        }
        OrderDTO orderDTO = queryOrderByTradeNo(tradeNo);
        OrderDTO updateDB = new OrderDTO();
        updateDB.setId(orderDTO.getId());
        updateDB.setUpdatedAt(new Date());

        String tradeStatus = params.get("trade_status");
        log.info("trade_status:{}",tradeStatus);
        if ("TRADE_SUCCESS".equals(tradeStatus) || "TRADE_FINISHED".equals(tradeStatus)) {
            updateDB.setOrderStatus(OrderStatusEnum.PAID.name());
        }else if ("TRADE_CLOSED".equals(tradeStatus)){
            updateDB.setOrderStatus(OrderStatusEnum.CLOSE.name());
        }
        updateOrder(updateDB);
        log.info("更新订单为支付成功 ！");
        return "ok";
    }

    public ResponseVo refundApply(long orderId) {
        ResponseVo responseVo = new ResponseVo();
        OrderDTO orderDTO = orderMapper.selectById(orderId);
        if (orderDTO == null) {
            responseVo.setCode("404");
            responseVo.setMsg("订单不存在");
            return  responseVo;
        }
        if(!canRefund(orderDTO)) {
            responseVo.setCode("100");
            responseVo.setMsg("订单不允许退款");
            return  responseVo;
        }

        RefundTDO refundTDO = new RefundTDO();
        refundTDO.setOrderId(orderId);
        refundTDO.setRefundAmount(orderDTO.getTotalAmount());
        refundTDO.setRefundStatus(RefundStatusEnum.CREATE.name());
        refundTDO.setUserId(orderDTO.getUserId());
        refundTDO.setOutRequestNo("R" + orderDTO.getTradeNo());
        refundTDO.setCreatedAt(new Date());
        refundTDO.setUpdatedAt(new Date());
        refundMapper.insert(refundTDO);

        return responseVo;
    }

    private boolean canRefund(OrderDTO orderDTO) {
        if (orderDTO.getOrderStatus().equals(OrderStatusEnum.PAID.name())) {
            return true;
        }
        return false;
    }

    public List<RefundTDO> queryToRefundList() {
        List<String> statusList = new ArrayList<>();
        statusList.add(RefundStatusEnum.CREATE.name());
        statusList.add(RefundStatusEnum.PAID_FAIL.name());
        statusList.add(RefundStatusEnum.PAID_TIMEOUT.name());
        QueryWrapper<RefundTDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("refund_status", statusList);
        List<RefundTDO> list = refundMapper.selectList(queryWrapper);
        return list;
    }


    public void updateRefundStatus(RefundTDO refundTDO) {
        refundTDO.setUpdatedAt(new Date());
        refundMapper.updateById(refundTDO);
    }
}
