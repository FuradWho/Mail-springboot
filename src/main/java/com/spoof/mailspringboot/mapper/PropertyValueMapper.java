package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.Property;
import com.spoof.mailspringboot.pojo.PropertyValue;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import javax.swing.*;
import java.util.List;

public interface PropertyValueMapper {

    @Select("select * from propertyvalue where pid = #{productId}")
    @Results(id = "propertyValuesMap",value = {
            @Result(column = "id",property = "PropertyValueId",id = true),
            @Result(column = "pid",property = "PropertyValueProduct",one = @One(select = "com.spoof.mailspringboot.mapper.ProductMapper.findByPid",fetchType = FetchType.LAZY)),
            @Result(column = "ptid",property = "PropertyValueProperty",one = @One(select = "com.spoof.mailspringboot.mapper.PropertyMapper.findPropertyById",fetchType = FetchType.LAZY)),
            @Result(column = "value",property = "value")
    })
    List<PropertyValue> findByProductOrderByIdDesc(Product product);


    @Select("select * from propertyvalue where pid = #{productId} and ptid = #{PropertyId}")
    @Results(id = "propertyValueMap",value = {
            @Result(column = "id",property = "PropertyValueId",id = true),
            @Result(column = "pid",property = "PropertyValueProduct",one = @One(select = "com.spoof.mailspringboot.mapper.ProductMapper.findByPid",fetchType = FetchType.LAZY)),
            @Result(column = "ptid",property = "PropertyValueProperty",one = @One(select = "com.spoof.mailspringboot.mapper.PropertyMapper.findPropertyById",fetchType = FetchType.LAZY)),
            @Result(column = "value",property = "value")
    })
    PropertyValue getByPropertyAndProduct(Property property, Product product);

}
