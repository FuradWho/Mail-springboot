package com.spoof.mailspringboot.service.Impl;


import com.spoof.mailspringboot.mapper.ReviewMapper;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.Review;
import com.spoof.mailspringboot.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {


    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Review> findByProduct(Product product) {
        return reviewMapper.findByProduct(product);
    }

    @Override
    public int countByProduct(Product product) {
        return reviewMapper.countByProduct(product);
    }
}
