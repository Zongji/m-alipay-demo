package com.demo.mvc.controller;

import com.demo.mvc.dto.PageResponseVo;
import com.demo.mvc.dto.ProductDTO;
import com.demo.mvc.dto.ResponseVo;
import com.demo.mvc.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/list")
    public PageResponseVo list(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize) {
        log.info("list page:{}, pageSize:{}", page, pageSize);
        List<ProductDTO> res = productService.listPage(page, pageSize);

        PageResponseVo responseVo = new PageResponseVo();
        responseVo.setList(res);
        responseVo.setPage(page);
        responseVo.setPageSize(pageSize);
        return responseVo;
    }

    @GetMapping("/detail")
    public ResponseVo detail(@RequestParam("code") String code) {
        log.info("detail code:{}", code);
        ProductDTO productDTO = productService.getProductByCode(code);

        ResponseVo responseVo = new ResponseVo();
        responseVo.setData(productDTO);

        return responseVo;
    }
}
