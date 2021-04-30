package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.mapper.UserMapper;
import com.spoof.mailspringboot.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    /**
     * 校验 判断该账户是否存在
     * @param name
     * @return
     */
    boolean isExist(String name);

    /**
     * 校验 通过账号密码获取该用户信息
     * @param name
     * @param password
     * @return
     */
    User getUserByNameAndPwd(String name,String password);

    /**
     * 添加用户
     * @param user
     */
    void addUser(User user);

    /**
     * 校验 通过账户获取用户信息
     * @param name
     * @return
     */
    User getUserByName(String name);

    /**
     * 更新账号密码
     * @param user
     */

    boolean updateUserPwd(User user);

}
