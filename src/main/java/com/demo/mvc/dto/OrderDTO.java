package com.demo.mvc.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("order")
public class OrderDTO {
    private String tradeNo;
    private String subject;
    private BigDecimal amount;
    private String productCode;


}
