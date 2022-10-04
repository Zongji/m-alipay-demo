package com.demo.mvc.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageResponseVo<T> {
    private String code;

    private String msg;
    private Integer page;
    private Integer pageSize;
    private Integer total;

    private List<T> list;

    public PageResponseVo() {
        this.code = "0000";
        this.msg = "ok";
        this.page = 1;
        this.pageSize = 10;
        this.list = new ArrayList<>();
        this.total = 0;
    }
}
