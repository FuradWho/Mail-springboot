package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.pojo.OrderItem;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {

    List<ProductImage> listSingleProductImages(Product product);

    List<ProductImage> listDetailProductImages(Product product);

    ProductImage findProductImageById(int id);

    void setFirstProdutImage(Product product);

    void setFirstProdutImage(List<Product> products);


    void setFirstProdutImagesOnOrderItems(List<OrderItem> orderItems);

    void setFirstProdutImages(List<Product> products);
}
