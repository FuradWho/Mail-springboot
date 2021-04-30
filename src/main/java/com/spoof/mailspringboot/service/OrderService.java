package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.pojo.Order;
import com.spoof.mailspringboot.pojo.User;

import java.util.List;

public interface OrderService {

    String waitPay = "waitPay";
    String waitDelivery = "waitDelivery";
    String waitConfirm = "waitConfirm";
    String waitReview = "waitReview";
    String finish = "finish";
    String delete = "delete";

    List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);

}
