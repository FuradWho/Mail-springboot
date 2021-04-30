package com.spoof.mailspringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "handler" })
public class Product {

    private Integer productId;
    private String productName;//产品名称
    private String productSubTitle;//小标题
    private float productOriginalPrice;//原始价格
    private float productPromotePrice;//优惠价格
    private Integer productStock;//库存
    private Date productCreateDate;//创建日期
    private Category productCategory;
    private ProductImage firstProductImage;    //产品图片对象，因为可能一个产品包含多个图片对象，所以这里接收的是序列化的图片对象
    private List<ProductImage> productSingleImages; //单个产品图片集合
    private List<ProductImage> productDetailImages; //详情产品图片集合
    private Integer reviewCount; //销量
    private Integer saleCount; //累计评价



    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductSubTitle() {
        return productSubTitle;
    }

    public void setProductSubTitle(String productSubTitle) {
        this.productSubTitle = productSubTitle;
    }

    public float getProductOriginalPrice() {
        return productOriginalPrice;
    }

    public void setProductOriginalPrice(float productOriginalPrice) {
        this.productOriginalPrice = productOriginalPrice;
    }

    public float getProductPromotePrice() {
        return productPromotePrice;
    }

    public void setProductPromotePrice(float productPromotePrice) {
        this.productPromotePrice = productPromotePrice;
    }

    public Integer getProductStock() {
        return productStock;
    }

    public void setProductStock(Integer productStock) {
        this.productStock = productStock;
    }

    public Date getProductCreateDate() {
        return productCreateDate;
    }

    public void setProductCreateDate(Date productCreateDate) {
        this.productCreateDate = productCreateDate;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public ProductImage getFirstProductImage() {
        return firstProductImage;
    }

    public void setFirstProductImage(ProductImage firstProductImage) {
        this.firstProductImage = firstProductImage;
    }

    public List<ProductImage> getProductSingleImages() {
        return productSingleImages;
    }

    public void setProductSingleImages(List<ProductImage> productSingleImages) {
        this.productSingleImages = productSingleImages;
    }

    public List<ProductImage> getProductDetailImages() {
        return productDetailImages;
    }

    public void setProductDetailImages(List<ProductImage> productDetailImages) {
        this.productDetailImages = productDetailImages;
    }

    public Integer getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(Integer reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Integer getSaleCount() {
        return saleCount;
    }

    public void setSaleCount(Integer saleCount) {
        this.saleCount = saleCount;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", productSubTitle='" + productSubTitle + '\'' +
                ", productOriginalPrice=" + productOriginalPrice +
                ", productPromotePrice=" + productPromotePrice +
                ", productStock=" + productStock +
                ", productCreateDate=" + productCreateDate +
                ", productCategory=" + productCategory +
                ", firstProductImage=" + firstProductImage +
                ", productSingleImages=" + productSingleImages +
                ", productDetailImages=" + productDetailImages +
                ", reviewCount=" + reviewCount +
                ", saleCount=" + saleCount +
                '}';
    }
}
