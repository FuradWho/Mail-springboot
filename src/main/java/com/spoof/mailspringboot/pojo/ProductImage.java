package com.spoof.mailspringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class ProductImage {

    private Integer productImageId;
    private String productImageType;
    private Product productImageProduct;

    public Integer getProductImageId() {
        return productImageId;
    }

    public void setProductImageId(Integer productImageId) {
        this.productImageId = productImageId;
    }

    public String getProductImageType() {
        return productImageType;
    }

    public void setProductImageType(String productImageType) {
        this.productImageType = productImageType;
    }

    public Product getProductImageProduct() {
        return productImageProduct;
    }

    public void setProductImageProduct(Product productImageProduct) {
        this.productImageProduct = productImageProduct;
    }

    @Override
    public String toString() {
        return "ProductImage{" +
                "productImageId=" + productImageId +
                ", productImageType='" + productImageType + '\'' +
                ", productImageProduct=" + productImageProduct +
                '}';
    }
}
