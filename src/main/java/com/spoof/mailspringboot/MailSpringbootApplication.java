package com.spoof.mailspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author 13375
 */

@EnableCaching
@SpringBootApplication
@MapperScan("com.spoof.mailspringboot.mapper")
@ComponentScan(basePackages = {"com.spoof.mailspringboot.*"})
//@ComponentScan(basePackageClasses = {com.spoof.mailspringboot.realm.ShiroConfiguration.class})
public class MailSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(MailSpringbootApplication.class, args);
    }

}
