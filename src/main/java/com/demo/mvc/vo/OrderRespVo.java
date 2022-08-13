package com.demo.mvc.vo;

import com.demo.mvc.dto.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRespVo {
    private Long id;
    private Long userId;
    private Long productId;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String tradeNo;
    private String subject;

    private ProductDTO productDTO;
}
