package com.demo.mvc.vo;

import lombok.Data;

@Data
public class ResponseVo<T> {
    private String code;
    private String msg;
    private T data;

    public ResponseVo(){
        this.code = "0000";
        this.msg = "ok";
    }
}
