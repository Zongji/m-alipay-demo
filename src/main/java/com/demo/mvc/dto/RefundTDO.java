package com.demo.mvc.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
public class RefundTDO {
    private Long id;
    @TableField("CREATED_AT")
    private Date createdAt;
    @TableField("UPDATED_AT")
    private Date updatedAt;
    @TableField("USER_ID")
    private Long userId;
    @TableField("ORDER_ID")
    private Long orderId;
    @TableField("TRADE_NO")
    private String tradeNo;
    @TableField("REFUND_AMOUNT")
    private BigDecimal refundAmount;
    @TableField("REFUND_STATUS")
    private String refundStatus;
    @TableField("REMARK")
    private String remark;

}
