package com.demo.mvc.dto;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("product")
public class ProductDTO{
    private Long id;
    private String name;
    private String productCode;
    private String categoryCode;
    private BigDecimal price;
    private String description;
    private int stock;
}
