package com.spoof.mailspringboot.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @author 13375
 */
@Controller
public class ForePageController {
    /**
     * 首页
     */
    @RequestMapping("/home")
    public String home() {
        return "fore/home";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:home";
    }

    /**
     * 注册页
     */
    @RequestMapping(value = "/register")
    public String register() {
        return "fore/register";
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = "/login")
    public String login() {
        return "fore/login";
    }

    @RequestMapping(value = "/userInfo")
    public String userInfo() {
        return "fore/userInfo";
    }


    /**
     * 注册成功页面
     *
     * @return
     */
    @RequestMapping(value = "/registerSuccess")
    public String registerSuccess() {
        return "fore/registerSuccess";
    }


    /**
     * 用户登出 通过shiro进行登出
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/forelogout")
    public String logout(HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            //默认清除session = session.removeAttribute("user")
            subject.logout();
        }
        return "redirect:home";
    }

    /**
     * 个人信息页面
     *
     * @return
     */
    @RequestMapping(value = "/forgetPassword")
    public String forgetPassword() {
        return "fore/forgetPassword";
    }

    /**
     * 商品页面
     */
    @RequestMapping(value = "/product")
    public String product() {
        return "fore/product";
    }

    /**
     * 分类页面
     */
    @RequestMapping(value = "/category")
    public String category() {
        return "fore/category";
    }

    /**
     * 搜索结果页面
     */
    @RequestMapping(value = "/search")
    public String searchResult() {
        return "fore/search";
    }


    /**
     * 立即购买页面
     */
    @RequestMapping(value = "/buy")
    public String buy() {
        return "fore/buy";
    }

    /**
     *  查看购物车页面
     */
    @RequestMapping(value = "/cart")
    public String cart() {
        return "fore/cart";
    }

    /**
     * 支付页面
     */
    @RequestMapping(value = "/alipay")
    public String alipay() {
        return "fore/alipay";
    }

    /**
     * 支付成功页面
     */
    @RequestMapping(value = "/payed")
    public String payed() {
        return "fore/payed";
    }

    /**
     *   我的订单页，即已购买
     */
    @RequestMapping(value = "/bought")
    public String bought() {
        return "fore/bought";
    }


    /**
     * 确认收货页面
     */
    @RequestMapping(value = "/confirmPay")
    public String confirmPay() {
        return "fore/confirmPay";
    }

    /**
     * 确认收货页面中的确认支付页面
     */
    @RequestMapping(value = "/orderConfirmed")
    public String orderConfirmed() {
        return "fore/orderConfirmed";
    }


    /**
     *  评价页面
     */
    @RequestMapping(value = "/review")
    public String review() {
        return "fore/review";
    }


}
