package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.pojo.Order;
import com.spoof.mailspringboot.pojo.OrderItem;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.User;

import java.util.List;

public interface OrderItemService {

    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    List<OrderItem> findByProduct(Product product);

    List<OrderItem> findByUserAndOrderIsNull(User user);

    int getSaleCount(Product product);

    void updateOrderItem(OrderItem orderItem);

    void addOrderItem(OrderItem orderItem);

    OrderItem findByOrderItemId(int id);
}
