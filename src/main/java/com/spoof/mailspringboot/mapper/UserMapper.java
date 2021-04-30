package com.spoof.mailspringboot.mapper;


import com.spoof.mailspringboot.pojo.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {

    /**
     * 注册时判断用户是否存在
     *
     * @param name
     * @return
     */
    @Select("select id,name,password,salt from user where name=#{name}")
    @Results(id = "userMap", value = {
            @Result(property = "userId", column = "id", id = true),
            @Result(property = "userName", column = "name"),
            @Result(property = "userPassword", column = "password"),
            @Result(property = "userSalt", column = "salt")
    })
    User findUserByName(@Param("name") String name);

    /**
     * 登录时通过账号密码判断是否存在该用户进行登录
     *
     * @param name
     * @param password
     * @return
     */
    @Select("select id,name,password,salt from user where name=#{name} and password=#{password}")
    @ResultMap(value = "userMap")
    User findUserByNameAndPwd(@Param("name") String name, @Param("password") String password);

    /**
     * 添加用户操作
     *
     * @param user
     */
    @Insert("insert into user(name,password,salt) values (#{userName},#{userPassword},#{userSalt})")
    @Options(useGeneratedKeys = true, keyProperty = "userId",keyColumn = "id")
    void saveUser(User user);


    @Update("update user set password = #{userPassword},salt=#{userSalt} where name=#{userName}")
    int updateUserPwd(User user);

    @Select("select * from user where id = #{id}")
    @Results(value = {
            @Result(property = "userId", column = "id", id = true),
            @Result(property = "userName", column = "name")
    })
    User findUserById(@Param("id") int id);


}
