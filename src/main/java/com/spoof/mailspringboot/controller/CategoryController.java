package com.spoof.mailspringboot.controller;


import com.spoof.mailspringboot.pojo.Category;
import com.spoof.mailspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @RequestMapping("/get")
    @ResponseBody
    public List<Category> get(){
        return categoryService.getCategoryList();
    }




}
