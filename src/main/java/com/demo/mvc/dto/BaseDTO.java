package com.demo.mvc.dto;

import lombok.Data;

import java.util.Date;

@Data
public class BaseDTO {
    private Long id;
    private Date createdAt = new Date();
    private Date updatedAt;
}
