package com.spoof.mailspringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(value = { "handler" })
public class Review {

    private int ReviewId;
    private User ReviewUser;
    private Product ReviewProduct;
    private String ReviewContent;
    private Date ReviewCreateDate;

    public int getReviewId() {
        return ReviewId;
    }

    public void setReviewId(int reviewId) {
        ReviewId = reviewId;
    }

    public User getReviewUser() {
        return ReviewUser;
    }

    public void setReviewUser(User reviewUser) {
        ReviewUser = reviewUser;
    }

    public Product getReviewProduct() {
        return ReviewProduct;
    }

    public void setReviewProduct(Product reviewProduct) {
        ReviewProduct = reviewProduct;
    }

    public String getReviewContent() {
        return ReviewContent;
    }

    public void setReviewContent(String reviewContent) {
        ReviewContent = reviewContent;
    }

    public Date getReviewCreateDate() {
        return ReviewCreateDate;
    }

    public void setReviewCreateDate(Date reviewCreateDate) {
        ReviewCreateDate = reviewCreateDate;
    }

    @Override
    public String toString() {
        return "Review{" +
                "ReviewId=" + ReviewId +
                ", ReviewUser=" + ReviewUser +
                ", ReviewProduct=" + ReviewProduct +
                ", ReviewContent='" + ReviewContent + '\'' +
                ", ReviewCreateDate=" + ReviewCreateDate +
                '}';
    }
}
