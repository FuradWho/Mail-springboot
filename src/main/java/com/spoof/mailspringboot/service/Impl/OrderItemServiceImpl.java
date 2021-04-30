package com.spoof.mailspringboot.service.Impl;

import com.spoof.mailspringboot.mapper.OrderItemMapper;
import com.spoof.mailspringboot.pojo.Order;
import com.spoof.mailspringboot.pojo.OrderItem;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.User;
import com.spoof.mailspringboot.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderItemServiceImpl implements OrderItemService {

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Override
    public List<OrderItem> findByOrderOrderByIdDesc(Order order) {
        return orderItemMapper.findByOrderOrderByIdDesc(order);
    }

    @Override
    public List<OrderItem> findByProduct(Product product) {
        return orderItemMapper.findByProduct(product);
    }

    @Override
    public List<OrderItem> findByUserAndOrderIsNull(User user) {
        return orderItemMapper.findByUserAndOrderIsNull(user);
    }

    @Override
    public int getSaleCount(Product product) {
        return orderItemMapper.getSaleCount(product);
    }

    @Override
    public void updateOrderItem(OrderItem orderItem) {
        orderItemMapper.updateOrderItem(orderItem);
    }

    @Override
    public void addOrderItem(OrderItem orderItem) {
        orderItemMapper.addOrderItem(orderItem);
    }

    @Override
    public OrderItem findByOrderItemId(int id) {
        return orderItemMapper.findByOrderItemId(id);
    }
}
