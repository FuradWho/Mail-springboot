package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.pojo.Category;
import com.spoof.mailspringboot.pojo.Product;

import java.util.List;

public interface ProductService {

    List<Product> findByCategoryOrderById(Category category);

    Product findByPid(int pid);

    void setSaleAndReviewNumber(Product product);

    void setSaleAndReviewNumber(List<Product> products);

    void setFirstProdutImage(Product product);

    void fillByRow(List<Category> categorys);

    List<Product> listByCategory(Category category);

    void fill(Category category);

    void fill(List<Category> categorys);

    void removeCategoryFromProduct(List<Category> categories);

    void removeCategoryFromProduct(Category category);

}
