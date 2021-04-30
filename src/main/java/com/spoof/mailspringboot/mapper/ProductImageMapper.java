package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.ProductImage;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductImageMapper {

    @Select("select * from productimage where pid = #{product.productId} and type = #{type}")
    @Results(id = "productImageMap" , value = {
            @Result(property = "productImageId",column = "id" ,id = true),
            @Result(property = "productImageType",column = "type"),
            @Result(property = "productImageProduct",column = "pid",one = @One(select = "com.spoof.mailspringboot.mapper.ProductMapper.findByPid",fetchType = FetchType.LAZY))
    })
    List<ProductImage> findByProductAndTypeOrderByIdDesc(Product product, String type);

    @Select("select * from productimage where id = #{id}")
    @ResultMap(value = "productImageMap")
    ProductImage findProductImageById(@Param("id") int id);

}
