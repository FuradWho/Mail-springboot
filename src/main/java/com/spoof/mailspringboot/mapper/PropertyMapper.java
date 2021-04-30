package com.spoof.mailspringboot.mapper;

import com.spoof.mailspringboot.pojo.Category;
import com.spoof.mailspringboot.pojo.Property;
import jdk.dynalink.linker.LinkerServices;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface PropertyMapper {

    @Select("select * from property where id = #{id}")
    @Results(id = "propertyMap",value = {
            @Result(column = "id",property = "PropertyId" ,id = true),
            @Result(column = "cid",property = "PropertyCategory",one = @One(select = "com.spoof.mailspringboot.mapper.CategoryMapper.getCategoryByCid",fetchType = FetchType.LAZY)),
            @Result(column = "name",property = "PropertyName" )
    })
    Property findPropertyById(@Param("id")int id );

    @Select("select * from property where cid = #{categoryId}")
    @ResultMap(value = "propertyMap")
    List<Property> getPropertiesByCategory(Category category);


}
