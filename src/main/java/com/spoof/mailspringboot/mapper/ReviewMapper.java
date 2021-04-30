package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.Review;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ReviewMapper {

    @Select("select * from review where pid =#{productId}")
    @Results(id = "reviewsMap",value = {
            @Result(column = "id",property = "ReviewId",id = true),
            @Result(column = "content",property = "ReviewContent"),
            @Result(column = "uid",property = "ReviewUser",one = @One(select = "com.spoof.mailspringboot.mapper.UserMapper.findUserById",fetchType = FetchType.LAZY)),
            @Result(column = "createDate",property = "ReviewCreateDate")
    })
    List<Review> findByProduct(Product product);



    @Select("select count(*) from review where pid = #{productId}")
    int countByProduct(Product product);
}
