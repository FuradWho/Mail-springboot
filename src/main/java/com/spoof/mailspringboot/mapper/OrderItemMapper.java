package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Order;
import com.spoof.mailspringboot.pojo.OrderItem;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface OrderItemMapper {


    /**
     * 一个订单可能包含多个商品
     *
     * @param order
     * @return
     */
    @Select("select * from orderitem where oid = #{OrderId}")
    @Results(id = "orderItemMap", value = {
            @Result(column = "id", property = "OrderItemId", id = true),
            @Result(column = "pid", property = "OrderItemProduct", one = @One(select = "com.spoof.mailspringboot.mapper.ProductMapper.findByPid", fetchType = FetchType.LAZY)),
            @Result(column = "oid", property = "OrderItemOrder", one = @One(select = "com.spoof.mailspringboot.mapper.OrderMapper.findByOid", fetchType = FetchType.LAZY)),
            @Result(column = "uid", property = "OrderItemUser", one = @One(select = "com.spoof.mailspringboot.mapper.UserMapper.findUserById", fetchType = FetchType.LAZY)),
            @Result(column = "number", property = "OrderItemNumber"),
    })
    List<OrderItem> findByOrderOrderByIdDesc(Order order);

    /**
     * 通过产品找对应的订单数 一个product主键id对应多个Pid
     *
     * @param product
     * @return
     */
    @Select("select * from orderitem where pid = #{productId}")
    @ResultMap(value = "orderItemMap")
    List<OrderItem> findByProduct(Product product);


    /**
     * 基于用户对象user，查询没有生成订单的订单项集合
     *
     * @param user
     * @return
     */
    @Select("select * from orderitem where uid = #{userId}")
    @ResultMap(value = "orderItemMap")
    List<OrderItem> findByUserAndOrderIsNull(User user);

    /**
     * 根据id查询OrderItem
     *
     * @param id
     * @return
     */
    @Select("select * from orderitem where id = #{id}")
    @ResultMap(value = "orderItemMap")
    OrderItem findOrderItemById(@Param("id") int id);


    @Select("select count(*) from orderitem where pid = #{productId}")
    int getSaleCount(Product product);

    @Update("update orderitem set number = #{OrderItemNumber} where id=#{OrderItemId}")
    void updateOrderItem(OrderItem orderItem);


    @Insert("insert into orderitem (pid,oid,uid,number)values (#{OrderItemProduct.productId},#{OrderItemOrder.OrderId},#{OrderItemUser.userId},#{OrderItemNumber})")
    @Options(useGeneratedKeys = true, keyProperty = "OrderItemId",keyColumn = "id")
    void addOrderItem(OrderItem orderItem);

    @Select("select * from orderitem where id = #{id}]")
    @ResultMap(value = "orderItemMap")
    OrderItem findByOrderItemId(@Param("id") int id);


}
