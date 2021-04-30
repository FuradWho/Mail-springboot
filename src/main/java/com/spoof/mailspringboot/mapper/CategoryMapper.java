package com.spoof.mailspringboot.mapper;


import com.spoof.mailspringboot.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author 13375
 */

public interface CategoryMapper {

    @Select("select * from category")
    @Results(id = "categoryMap",value = {
            @Result(property = "categoryId",column = "id",id = true),
            @Result(property = "categoryName",column = "name")
    })
    List<Category> getAllCategory();

    @Select("select * from category where id = #{id}")
    @ResultMap(value = "categoryMap")
    Category getCategoryByCid(@Param("id") int id);

}
