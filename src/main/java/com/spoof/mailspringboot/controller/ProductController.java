package com.spoof.mailspringboot.controller;


import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.service.ProductImageService;
import com.spoof.mailspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    //添加Product数据
    //添加数据 接收从前台传过来的json数据
    @RequestMapping(value = "/products",method = RequestMethod.POST)
    @ResponseBody
    public Product add(@RequestBody Product product){
       product.setProductCreateDate(new Date());
       return product;
    }




}
