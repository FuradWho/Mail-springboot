package com.spoof.mailspringboot.realm;

import com.spoof.mailspringboot.pojo.User;
import com.spoof.mailspringboot.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

/**
 * 这个类，用户提供，但是不由用户自己调用，而是由 Shiro 去调用
 * shiro的中介域Realm 在项目中用于验证账号登录和授权、账号密码加密
 */
public class ShiroRealm extends AuthorizingRealm {


    @Autowired
    @Lazy
    private UserService userService;

    /**
     * 权限控制 能进入到这里，表示账号已经通过验证了, 直接返回一个授权对象即可
     * 这个是当登陆成功之后会被调用，看当前的登陆角色是有有权限来进行操作
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 校验授权
     * 用于认证登录，认证接口实现方法，该方法的回调一般是通过subject.login(token)方法来实现的
     *
     * AuthenticationToken 用于收集用户提交的身份（如用户名）及凭据（如密码）：
     * 	 AuthenticationInfo是包含了用户根据username返回的数据信息，用于在匹马比较的时候进行相互比较
     *
     * 	 shiro的核心是java servlet规范中的filter，通过配置拦截器，使用拦截器链来拦截请求，如果允许访问，则通过。
     * 	 通常情况下，系统的登录、退出会配置拦截器。登录的时候，调用subject.login(token),token是用户验证信息，
     * 	 这个时候会在Realm中doGetAuthenticationInfo方法中进行认证。这个时候会把用户提交的验证信息与数据库中存储的认证信息，将所有的数据拿到，在匹配器中进行比较
     * 	 这边是我们自己实现的CredentialMatcher类的doCredentialsMatch方法，返回true则一致，false则登陆失败
     * 	 退出的时候，调用subject.logout()，会清除回话信息

     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //通过shiro的配置从前端传入用户名获取对应用户，同时保存传入的密码
        //如果对应用户存在的情况下，即从数据库中取出对应的密码和盐
        //前端传入的密码配合盐进行两次md5加密
        //加密后和从数据库中获取的密码进行对比，如果相同，则返回一个授权对象

        //这里要配置好shiro才能获取到对应的token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //因为这个Realm是由shiro去调用操作的
        String userName = token.getUsername();
        String userPassword = new String(token.getPassword());
        //通过用户名获取对应用户对象
        User user = userService.getUserByName(userName);
        //获取数据库中的密码
        String password = user.getUserPassword();
        //获取盐
        String salt = user.getUserSalt();
        //使用盐对前端的密码进行二次加密
        String passwordEncoded = new SimpleHash("md5", userPassword, salt, 2).toString();
        //二次加密的值与密码进行对比
        if (null == user || !passwordEncoded.equals(password)) {
            throw new AuthenticationException();
        }
        //对比成功后返回授权对象，并将用户名和密码存入
        SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(userName, userPassword, getName());
        return simpleAuthenticationInfo;
    }
}
