package com.spoof.mailspringboot.test;

import com.spoof.mailspringboot.service.CategoryService;
import com.spoof.mailspringboot.service.Impl.CategoryServiceImpl;

import com.spoof.mailspringboot.pojo.Category;
import org.junit.Test;

import java.util.List;

public class CategoryServiceTest {

    @Test
    public void testGetCategoryList(){
        CategoryService categoryService = new CategoryServiceImpl();
        List<Category> categoryList =  categoryService.getCategoryList();

        for (Category c: categoryList) {
            System.out.println(c);
        }

    }
}
