package com.demo.mvc.dto;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private String productCode;
    private BigDecimal price;
    private String desrciption;
}
