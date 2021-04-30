package com.spoof.mailspringboot.service.Impl;

import com.spoof.mailspringboot.mapper.OrderMapper;
import com.spoof.mailspringboot.pojo.Order;
import com.spoof.mailspringboot.pojo.User;
import com.spoof.mailspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status) {
        return orderMapper.findByUserAndStatusNotOrderByIdDesc(user,status);
    }
}
