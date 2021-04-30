package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.mapper.ReviewMapper;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.Review;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface ReviewService {

    List<Review> findByProduct(Product product);

    int countByProduct(Product product);
}
