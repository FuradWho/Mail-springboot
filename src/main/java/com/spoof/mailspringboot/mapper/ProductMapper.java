package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Category;
import com.spoof.mailspringboot.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface ProductMapper {


    @Select("select * from product p left join category c on p.cid = c.id where c.id = #{categoryId}")
    @Results(id = "productsMap",value = {
            @Result(property = "productId",column = "id",id = true),
            @Result(property = "productName",column = "name"),
            @Result(property = "productSubTitle",column = "subTitle"),
            @Result(property = "productOriginalPrice",column = "originalPrice"),
            @Result(property = "productPromotePrice",column = "promotePrice"),
            @Result(property = "productStock",column = "stock"),
            @Result(property = "productCreateDate",column = "createDate")
    })
    List<Product> findByCategoryOrderById(Category category);


    @Select("select * from product p left join category c on p.cid = c.id where p.id = #{pid}")
    @Results(id = "productMap",value = {
            @Result(property = "productId",column = "id",id = true),
            @Result(property = "productName",column = "name"),
            @Result(property = "productSubTitle",column = "subTitle"),
            @Result(property = "productOriginalPrice",column = "originalPrice"),
            @Result(property = "productPromotePrice",column = "promotePrice"),
            @Result(property = "productStock",column = "stock"),
            @Result(property = "productCategory",column = "cid",one = @One(select = "com.spoof.mailspringboot.mapper.CategoryMapper.getCategoryByCid",fetchType = FetchType.LAZY)),
            @Result(property = "productCreateDate",column = "createDate")
    })
    Product findByPid(@Param("pid") int pid);
}
