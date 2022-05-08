package com.demo.mvc.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Data
public class TradeDTO {
    private String tradeNo;
    private String subject;
    private BigDecimal amount;
    private String productCode;


}
