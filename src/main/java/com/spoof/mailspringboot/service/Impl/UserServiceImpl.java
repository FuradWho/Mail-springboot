package com.spoof.mailspringboot.service.Impl;

import com.spoof.mailspringboot.mapper.UserMapper;
import com.spoof.mailspringboot.pojo.User;
import com.spoof.mailspringboot.service.UserService;

import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean isExist(String name) {
        User user = getUserByName(name);
        System.out.println(user);
        if (null == user) {
            return false;
        } else {
            return true;
        }
    }

    @Cacheable(value = "users" , key = "#name")
    @Override
    public User getUserByNameAndPwd(String name, String password) {
        return userMapper.findUserByNameAndPwd(name,password);
    }

    @Override
    @CachePut(value = "users" ,key = "#user.userName")
    public void addUser(User user) {
        userMapper.saveUser(user);
    }

    @Override
    @CachePut(value = "users" ,key = "#name" )
    public User getUserByName(String name) {
        return userMapper.findUserByName(name);
    }

    @Override
    public boolean updateUserPwd(User user) {
       if( 1 == userMapper.updateUserPwd(user)){
           return true;
       }else {
           return false;
       }
    }
}
