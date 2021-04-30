package com.spoof.mailspringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(value = { "handler" })
public class OrderItem {

    private int OrderItemId;
    //一个用户的订单列中可能存在多个商品
    private Product OrderItemProduct;
    private Order OrderItemOrder;
    private User OrderItemUser;
    //商品订单购买数量
    private int OrderItemNumber;

    public int getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        OrderItemId = orderItemId;
    }

    public Product getOrderItemProduct() {
        return OrderItemProduct;
    }

    public void setOrderItemProduct(Product orderItemProduct) {
        OrderItemProduct = orderItemProduct;
    }

    public Order getOrderItemOrder() {
        return OrderItemOrder;
    }

    public void setOrderItemOrder(Order orderItemOrder) {
        OrderItemOrder = orderItemOrder;
    }

    public User getOrderItemUser() {
        return OrderItemUser;
    }

    public void setOrderItemUser(User orderItemUser) {
        OrderItemUser = orderItemUser;
    }

    public int getOrderItemNumber() {
        return OrderItemNumber;
    }

    public void setOrderItemNumber(int orderItemNumber) {
        OrderItemNumber = orderItemNumber;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "OrderItemId=" + OrderItemId +
                ", OrderItemProduct=" + OrderItemProduct +
                ", OrderItemOrder=" + OrderItemOrder +
                ", OrderItemUser=" + OrderItemUser +
                ", OrderItemNumber=" + OrderItemNumber +
                '}';
    }
}
