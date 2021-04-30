package com.spoof.mailspringboot.service.Impl;

import com.spoof.mailspringboot.mapper.CategoryMapper;
import com.spoof.mailspringboot.pojo.Category;
import com.spoof.mailspringboot.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<Category> getCategoryList() {
        return categoryMapper.getAllCategory();
    }

    @Override
    public Category getCategoryByCid(int cid) {
        return categoryMapper.getCategoryByCid(cid);
    }


}
