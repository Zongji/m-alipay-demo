package com.demo.mvc.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@TableName("e_user")
public class UserDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private String password;
}
