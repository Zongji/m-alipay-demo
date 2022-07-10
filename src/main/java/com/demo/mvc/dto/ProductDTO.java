package com.demo.mvc.dto;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("e_product")
public class ProductDTO{
    private Long id;
    private String name;

    @TableField("product_code")
    private String productCode;
    @TableField("category_code")
    private String categoryCode;
    private BigDecimal price;
    private String description;
    private int stock;
}
