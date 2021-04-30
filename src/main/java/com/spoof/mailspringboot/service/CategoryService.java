package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.pojo.Category;

import java.util.List;

public interface CategoryService {

     List<Category> getCategoryList();

     Category getCategoryByCid(int cid);
}
