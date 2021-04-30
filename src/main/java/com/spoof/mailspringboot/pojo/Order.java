package com.spoof.mailspringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spoof.mailspringboot.service.OrderService;

import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(value = { "handler" })
public class Order {

    private int OrderId;
    private User OrderUser;
    private String OrderCode;
    private String OrderAddress;
    private String OrderPost;
    private String OrderReceiver;
    private String OrderMobile;
    private String OrderUserMessage;
    private Date OrderCreateDate;
    private Date OrderPayDate;
    private Date OrderDeliverDate;
    private Date OrderConfirmDate;
    private String OrderStatus;

    private List<OrderItem> OrderItems;
    private float OrderTotal;
    private int OrderTotalNumber;
    private String OrderStatusDesc;

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }

    public User getOrderUser() {
        return OrderUser;
    }

    public void setOrderUser(User orderUser) {
        OrderUser = orderUser;
    }

    public String getOrderCode() {
        return OrderCode;
    }

    public void setOrderCode(String orderCode) {
        OrderCode = orderCode;
    }

    public String getOrderAddress() {
        return OrderAddress;
    }

    public void setOrderAddress(String orderAddress) {
        OrderAddress = orderAddress;
    }

    public String getOrderPost() {
        return OrderPost;
    }

    public void setOrderPost(String orderPost) {
        OrderPost = orderPost;
    }

    public String getOrderReceiver() {
        return OrderReceiver;
    }

    public void setOrderReceiver(String orderReceiver) {
        OrderReceiver = orderReceiver;
    }

    public String getOrderMobile() {
        return OrderMobile;
    }

    public void setOrderMobile(String orderMobile) {
        OrderMobile = orderMobile;
    }

    public String getOrderUserMessage() {
        return OrderUserMessage;
    }

    public void setOrderUserMessage(String orderUserMessage) {
        OrderUserMessage = orderUserMessage;
    }

    public Date getOrderCreateDate() {
        return OrderCreateDate;
    }

    public void setOrderCreateDate(Date orderCreateDate) {
        OrderCreateDate = orderCreateDate;
    }

    public Date getOrderPayDate() {
        return OrderPayDate;
    }

    public void setOrderPayDate(Date orderPayDate) {
        OrderPayDate = orderPayDate;
    }

    public Date getOrderDeliverDate() {
        return OrderDeliverDate;
    }

    public void setOrderDeliverDate(Date orderDeliverDate) {
        OrderDeliverDate = orderDeliverDate;
    }

    public Date getOrderConfirmDate() {
        return OrderConfirmDate;
    }

    public void setOrderConfirmDate(Date orderConfirmDate) {
        OrderConfirmDate = orderConfirmDate;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public List<OrderItem> getOrderItems() {
        return OrderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        OrderItems = orderItems;
    }

    public float getOrderTotal() {
        return OrderTotal;
    }

    public void setOrderTotal(float orderTotal) {
        OrderTotal = orderTotal;
    }

    public int getOrderTotalNumber() {
        return OrderTotalNumber;
    }

    public void setOrderTotalNumber(int orderTotalNumber) {
        OrderTotalNumber = orderTotalNumber;
    }

    public String getOrderStatusDesc() {
        if(null!=OrderStatusDesc) {
            return OrderStatusDesc;
        }
        String desc ="未知";
        switch (OrderStatus){
            case OrderService.waitPay:
                desc="待付";
                break;
            case OrderService.waitDelivery:
                desc="待发";
                break;
            case OrderService.waitConfirm:
                desc="待收";
                break;
            case OrderService.waitReview:
                desc="等评";
                break;
            case OrderService.finish:
                desc="完成";
                break;
            case OrderService.delete:
                desc="刪除";
                break;
            default:
                desc="未知";
        }
        OrderStatusDesc = desc;
        return OrderStatusDesc;
    }

    public void setOrderStatusDesc(String orderStatusDesc) {
        OrderStatusDesc = orderStatusDesc;
    }

    @Override
    public String toString() {
        return "Order{" +
                "OrderId=" + OrderId +
                ", OrderUser=" + OrderUser +
                ", OrderCode='" + OrderCode + '\'' +
                ", OrderAddress='" + OrderAddress + '\'' +
                ", OrderPost='" + OrderPost + '\'' +
                ", OrderReceiver='" + OrderReceiver + '\'' +
                ", OrderMobile='" + OrderMobile + '\'' +
                ", OrderUserMessage='" + OrderUserMessage + '\'' +
                ", OrderCreateDate=" + OrderCreateDate +
                ", OrderPayDate=" + OrderPayDate +
                ", OrderDeliverDate=" + OrderDeliverDate +
                ", OrderConfirmDate=" + OrderConfirmDate +
                ", OrderStatus=" + OrderStatus +
                ", OrderItems=" + OrderItems +
                ", OrderTotal=" + OrderTotal +
                ", OrderTotalNumber=" + OrderTotalNumber +
                ", OrderStatusDesc='" + OrderStatusDesc + '\'' +
                '}';
    }
}
