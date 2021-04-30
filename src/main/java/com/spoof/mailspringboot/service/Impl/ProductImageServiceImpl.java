package com.spoof.mailspringboot.service.Impl;

import com.spoof.mailspringboot.mapper.ProductImageMapper;
import com.spoof.mailspringboot.pojo.OrderItem;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.ProductImage;
import com.spoof.mailspringboot.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 13375
 */
@Service
public class ProductImageServiceImpl implements ProductImageService {
    //创建ProductImageService，提供CRD。
    //同时还提供了两个常量，分别表示单个图片和详情图片：
    public static final String type_single = "single";
    public static final String type_detail = "detail";

    @Autowired
    private ProductImageMapper productImageMapper;

    @Override
    public List<ProductImage> listSingleProductImages(Product product) {
        return productImageMapper.findByProductAndTypeOrderByIdDesc(product,type_single);
    }

    @Override
    public List<ProductImage> listDetailProductImages(Product product) {
        return productImageMapper.findByProductAndTypeOrderByIdDesc(product,type_detail);
    }

    @Override
    public ProductImage findProductImageById(int id) {
        return productImageMapper.findProductImageById(id);
    }

    @Override
    public void setFirstProdutImage(Product product) {
        List<ProductImage> productImageList = listSingleProductImages(product);

        if(!productImageList.isEmpty()){
            product.setFirstProductImage(productImageList.get(0));
        }else{
            product.setFirstProductImage(new ProductImage());
        }
    }

    @Override
    public void setFirstProdutImage(List<Product> products) {
        for (Product p: products) {
            setFirstProdutImage(p);
        }
    }

    @Override
    public void setFirstProdutImagesOnOrderItems(List<OrderItem> orderItems) {
        for (OrderItem orderItem : orderItems) {
            setFirstProdutImage(orderItem.getOrderItemProduct());
        }
    }

    @Override
    public void setFirstProdutImages(List<Product> products) {
        for (Product product : products) {
            setFirstProdutImage(product);
        }
    }


}
