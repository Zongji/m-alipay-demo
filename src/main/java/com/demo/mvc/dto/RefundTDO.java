package com.demo.mvc.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


@Data
@TableName("e_refund")
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
    @TableField("OUT_REQUEST_NO")
    private String outRequestNo;
    @TableField("REFUND_AMOUNT")
    private BigDecimal refundAmount;
    @TableField("REFUND_STATUS")
    private String refundStatus;
    @TableField("REMARK")
    private String remark;

}
