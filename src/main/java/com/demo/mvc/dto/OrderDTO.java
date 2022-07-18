package com.demo.mvc.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("e_order")
public class OrderDTO {
    private Long id;
    @TableField("USER_ID")
    private Long userId;
    @TableField("PRODUCT_ID")
    private Long productId;
    @TableField("PRICE")
    private BigDecimal price;
    @TableField("TOTAL_AMOUNT")
    private BigDecimal totalAmount;
    @TableField("ORDER_STATUS")
    private String orderStatus;
    @TableField("TRADE_NO")
    private String tradeNo;
    @TableField("SUBJECT")
    private String subject;

}
