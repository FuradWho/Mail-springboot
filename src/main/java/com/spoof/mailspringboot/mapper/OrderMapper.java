package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Order;
import com.spoof.mailspringboot.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface OrderMapper {


    @Select("select * from order_ where uid=#{user.userId} and status = #{status} ")
    @Results(id = "ordersMap",value = {
            @Result(column = "id",property = "OrderId",id = true),
            @Result(column = "orderCode",property = "OrderCode"),
            @Result(column = "address",property = "OrderAddress"),
            @Result(column = "post",property = "OrderPost"),
            @Result(column = "receiver",property = "OrderReceiver"),
            @Result(column = "mobile",property = "OrderMobile"),
            @Result(column = "userMessage",property = "OrderUserMessage"),
            @Result(column = "createDate",property = "OrderCreateDate"),
            @Result(column = "payDate",property = "OrderPayDate"),
            @Result(column = "deliveryDate",property = "OrderDeliverDate"),
            @Result(column = "confirmDate",property = "OrderConfirmDate"),
            @Result(column = "uid",property = "OrderUser",one = @One(select = "com.spoof.mailspringboot.mapper.UserMapper.findUserById",fetchType = FetchType.LAZY)),
            @Result(column = "status",property = "OrderStatus"),
    })
    List<Order> findByUserAndStatusNotOrderByIdDesc(User user, String status);

    @Select("select * from order_ where id=#{oid]")
    @ResultMap(value = "ordersMap")
    Order findByOid(@Param("oid")int oid);

}
