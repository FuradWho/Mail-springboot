package com.spoof.mailspringboot.realm;


import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

import java.util.LinkedHashMap;

/**
 * 自定义了Shiro的配置器
 *
 * @author 13375
 */

@Configuration
@ConditionalOnClass(PersistenceExceptionTranslationPostProcessor.class)
public class ShiroConfiguration {

    /**
     * 自定义了Shiro的过滤器
     *
     * @param manager
     * @return
     */

    @Bean
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(manager);

        //提供登录到url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //提供登陆成功的url
        shiroFilterFactoryBean.setSuccessUrl("/login");
        // 未授权的页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized.action");

        /*
         * 可以看DefaultFilter,这是一个枚举类，定义了很多的拦截器authc,anon等分别有对应的拦截器
         * 代表着前面的url路径，用后面指定的拦截器进行拦截
         * */
        LinkedHashMap<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //filterChainDefinitionMap.put("/index", "authc");
        //filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        //admin的url，要用角色是admin的才可以登录,对应的拦截器是RolesAuthorizationFilter
        //filterChainDefinitionMap.put("/admin", "roles[admin]");
        //拥有edit权限的用户才有资格去访问
        //filterChainDefinitionMap.put("/edit", "perms[edit]");
        //所有的druid请求，不需要拦截，anon对应的拦截器不会进行拦截
        //filterChainDefinitionMap.put("/druid/**", "anon");
        //所有的路径都拦截，被UserFilter拦截，这里会判断用户有没有登陆
        //filterChainDefinitionMap.put("/**", "user");
        // 最后一班都，固定格式
        //filterChainDefinitionMap.put("/**", "authc");
        //设置一个拦截器链
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;


    }

    /**
     * 注入一个securityManager
     * 原本以前可以通过ini配置文件完成的，代码如下：
     * 1、获取SecurityManager工厂，此处使用Ini配置文件初始化SecurityManager
     * Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
     * 2、得到SecurityManager实例 并绑定给SecurityUtils
     * SecurityManager securityManager = factory.getInstance();
     * SecurityUtils.setSecurityManager(securityManager);
     *
     * @param shiroRealm
     * @return
     */

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("shiroRealm") ShiroRealm shiroRealm) {
        //这个DefaultWebSecurityManager构造函数,会对Subject，realm等进行基本的参数注入
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        //往SecurityManager中注入Realm，代替原本的默认配置
        manager.setRealm(shiroRealm);
        // 注入缓存管理器;
      //  manager.setCacheManager(ehCacheManager());
        return manager;
    }

    /**
     * 凭证匹配器 （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码; )
     *
     * @param
     */
//    @Bean
//    public HashedCredentialsMatcher hashedCredentialsMatcher() {
//        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
//        // 散列算法:这里使用MD5算法;
//        hashedCredentialsMatcher.setHashAlgorithmName("md5");
//        // 散列的次数，比如散列两次，相当于md5(md5(""));
//        hashedCredentialsMatcher.setHashIterations(1024);
//        return hashedCredentialsMatcher;
//    }


    /**
     * ShiroRealm
     * @return
     */
    @Bean("shiroRealm")
    public ShiroRealm shiroRealm() {
        ShiroRealm shiroRealm = new ShiroRealm();
        return shiroRealm;
    }

    /**
     * 可以使用shiro帮助比较SimpleAuthenticationInfo 这个方法
     * @return
     */
//    @Bean("shiroRealm")
//    public ShiroRealm shiroRealm(){
//        ShiroRealm myShiroRealm = new ShiroRealm();
//        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
//        return myShiroRealm;
//    }

    /**
     * shiro缓存管理器;
     * 需要注入对应的其它的实体类中-->安全管理器：securityManager可见securityManager是整个shiro的核心；
     * @return
     */
//    @Bean
//    public EhCacheManager ehCacheManager() {
//        System.out.println("ShiroConfiguration.getEhCacheManager()");
//        EhCacheManager cacheManager = new EhCacheManager();
//        cacheManager.setCacheManagerConfigFile("classpath:ehcache.xml");
//        return cacheManager;
//    }


    /**
     * 以下AuthorizationAttributeSourceAdvisor,DefaultAdvisorAutoProxyCreator两个类是为了支持shiro注解
     *
     * @param securityManager
     * @return
     */
    @Bean
    @ConditionalOnMissingBean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }


}
