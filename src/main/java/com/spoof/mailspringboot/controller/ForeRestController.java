package com.spoof.mailspringboot.controller;


import com.spoof.mailspringboot.comparator.*;
import com.spoof.mailspringboot.pojo.*;
import com.spoof.mailspringboot.service.*;
import com.spoof.mailspringboot.util.ResultUtil;
import com.spoof.mailspringboot.util.SpringContextUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class ForeRestController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private PropertyValueService propertyValueService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderItemService orderItemService;

    /**
     * 注册映射,通过Shiro的方式进行校验
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/foreregister", method = RequestMethod.POST)
    @ResponseBody
    public Object register(@RequestBody User user) {
        String name = user.getUserName();
        String password = user.getUserPassword();

        System.out.println(user);
        //字符转义，避免恶意注册 如写入一段Js片段
        name = HtmlUtils.htmlEscape(name);
        user.setUserName(name);

        //校验用户是否存在
        boolean flag = userService.isExist(name);
        if (flag) {
            //失败返回1
            System.out.println(flag);
            return ResultUtil.fail("账号已存在，请登录！");
        } else {
            //注册时候的时候，会通过随机方式创建盐并且加密算法采用 "md5", 除此之外还会进行2次加密。
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            String passwordEncoded = new SimpleHash("md5", password, salt, 2).toString();
            //这个盐，如果丢失了，就无法验证密码是否正确了，所以会数据库里保存起来。
            user.setUserSalt(salt);
            //设置密码为盐+两次MD5加密后的结果
            user.setUserPassword(passwordEncoded);
            //用户添加到数据库中
            userService.addUser(user);
            //成功返回0
            return ResultUtil.success();
        }
    }


    /**
     * 登录映射，通过Shiro的方式进行校验
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping(value = "/forelogin", method = RequestMethod.POST)
    @ResponseBody
    public Object login(@RequestBody User user, HttpSession session) {
        //校验用户
        String name = user.getUserName();
        String password = user.getUserPassword();

        System.out.println(user);

        name = HtmlUtils.htmlEscape(name);
        //获取当前活动的对象Subject  Subject 在 Shiro 这个安全框架下， Subject 就是当前用户
        Subject subject = SecurityUtils.getSubject();
        //将账号名和密码送入shiro   UsernamePasswordToken 当中,Realm其中方法参数就是token
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            //在这传入token供Realm验证(实际上在这里已经调用了shiroConfiguration，其对应的Realm在监控当前login)
            //realm的方法之中会抛出异常，由该方法捕捉catch
            subject.login(token);
            //校验没错，没有触发异常，即可将用户保存在Session上
            User getUser = userService.getUserByName(name);
            session.setAttribute("user", getUser);
            return ResultUtil.success();
        } catch (AuthenticationException e) {
            return ResultUtil.fail("账号密码错误");
        }
    }

    /**
     * 检查登录映射（模态登录） 使用shiro检查登录
     *
     * @param session
     * @return
     */
    @RequestMapping(value = "/forecheckLogin",method = RequestMethod.GET)
    @ResponseBody
    public Object checkLogin(HttpSession session){

        //使用Shiro的Subject对象,当前活动的对象（即当前用于校验的user）
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            //通过验证
            return ResultUtil.success();
        }else{
            return ResultUtil.fail("未登录！");
        }
    }

    /**
     * 用户更新密码页面
     * @param user
     * @return
     */
    @RequestMapping(value = "/updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public Object updatePassword(@RequestBody User user){
        String name = user.getUserName();
        String password = user.getUserPassword();

        System.out.println(user);
        //字符转义，避免恶意注册 如写入一段Js片段
        name = HtmlUtils.htmlEscape(name);
        user.setUserName(name);

        //校验用户是否存在
        boolean flag = userService.isExist(name);
        if (flag) {
            //注册时候的时候，会通过随机方式创建盐并且加密算法采用 "md5", 除此之外还会进行2次加密。
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            String passwordEncoded = new SimpleHash("md5", password, salt, 2).toString();
            //这个盐，如果丢失了，就无法验证密码是否正确了，所以会数据库里保存起来。
            user.setUserSalt(salt);
            //设置密码为盐+两次MD5加密后的结果
            user.setUserPassword(passwordEncoded);
            //用户添加到数据库中
            userService.updateUserPwd(user);
            //成功返回0
            return ResultUtil.success();

        } else {
            //失败返回1
            System.out.println(flag);
            return ResultUtil.fail("账号未存在，请注册账号！");

        }
    }


    @RequestMapping(value = "/foreproduct/{pid}",method = RequestMethod.GET)
    @ResponseBody
    public Object product(@PathVariable("pid") int pid){

        Product product = productService.findByPid(pid);

        List<ProductImage> singleImages = productImageService.listSingleProductImages(product);
        List<ProductImage> detailImages = productImageService.listDetailProductImages(product);

        product.setProductSingleImages(singleImages);
        product.setProductDetailImages(detailImages);

        System.out.println(product);

        List<PropertyValue> propertyValues = propertyValueService.getPropertyValueListByProduct(product);
        List<Review> reviews =reviewService.findByProduct(product);

        productService.setSaleAndReviewNumber(product);
        productImageService.setFirstProdutImage(product);

        Map<String,Object> map = new HashMap<String, Object>();
        map.put("product",product);
        map.put("pvs",propertyValues);
        map.put("reviews",reviews);


        return ResultUtil.success(map);
    }


    @RequestMapping(value = "/forecategory/{cid}",method = RequestMethod.GET)
    @ResponseBody
    public Object category(@PathVariable("cid") int cid,String sort){
        Category category = categoryService.getCategoryByCid(cid);

        productService.fill(category);
        productService.setSaleAndReviewNumber(category.getProductList());
        productService.removeCategoryFromProduct(category);

        if(null != sort){
            switch (sort){
                case "review":
                    Collections.sort(category.getProductList(),new ProductReviewComparator());
                    break;
                case "date" :
                    Collections.sort(category.getProductList(),new ProductDateComparator());
                    break;

                case "saleCount" :
                    Collections.sort(category.getProductList(),new ProductSaleCountComparator());
                    break;

                case "price":
                    Collections.sort(category.getProductList(),new ProductPriceComparator());
                    break;

                case "all":
                    Collections.sort(category.getProductList(),new ProductAllComparator());
                    break;
            }
        }

        return category;
    }

    @RequestMapping(value = "/forehome",method = RequestMethod.GET)
    @ResponseBody
    public Object home(){
        List<Category> categoryList = categoryService.getCategoryList();
        productService.fill(categoryList);
        productService.fillByRow(categoryList);
        productService.removeCategoryFromProduct(categoryList);
        return categoryList;
    }


    @RequestMapping(value = "/forebuyone",method = RequestMethod.GET)
    @ResponseBody
    public Object buyone(@RequestParam("pid")int pid ,@RequestParam("num") int num ,HttpSession session){
        return buyoneAndAddCart(pid, num, session);
    }

    @RequestMapping(value = "foreaddCart",method = RequestMethod.GET)
    @ResponseBody
    public Object addCart(int pid, int num, HttpSession session) {
        buyoneAndAddCart(pid,num,session);
        return ResultUtil.success();
    }

    private int buyoneAndAddCart(int pid, int num, HttpSession session) {

        Product product =productService.findByPid(pid);
        User user = (User) session.getAttribute("user");
        boolean flag = false;
        int orderId = 0;

        List<OrderItem> orderItemList = orderItemService.findByUserAndOrderIsNull(user);

        for (OrderItem orderItem : orderItemList){
            if(orderItem.getOrderItemProduct().getProductId().equals(product.getProductId())){
                orderItem.setOrderItemNumber(orderItem.getOrderItemNumber()+num);
                orderItemService.updateOrderItem(orderItem);
                flag =true;
                orderId = orderItem.getOrderItemId();
                break;
            }
        }

        if (!flag){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrderItemUser(user);
            orderItem.setOrderItemProduct(product);
            orderItem.setOrderItemNumber(num);
            orderItemService.addOrderItem(orderItem);
            orderId = orderItem.getOrderItemId();
        }
        return orderId;
    }
    //利用Oiid获得对应订单项，在订单页中读出对应数据
    @RequestMapping("/forebuy")
    @ResponseBody
    public Object buy(String[] oiid,HttpSession session){
        //这里要用字符串数组试图获取多个oiid，而不是int类型仅仅获取一个oiid?
        // 因为根据购物流程环节与表关系，结算页面还需要显示在购物车中选中的多条OrderItem数据，
        // 所以为了兼容从购物车页面跳转过来的需求，要用字符串数组获取多个oiid
        List<OrderItem> orderItems = new ArrayList<OrderItem>(); //当商品在购物车被多选立即购物时，就需要接收多个OrderItem
        float total = 0;//用于计算总价
        for (String strid : oiid) {
            int id = Integer.parseInt(strid);
            OrderItem oi= orderItemService.findByOrderItemId(id);
            total += oi.getOrderItemProduct().getProductPromotePrice()*oi.getOrderItemNumber();
            //将当前的OrderItem添加到List<OrderItem>中
            orderItems.add(oi);
        }
        //为每个OrderItem对应的每个产品设置预览图，按顺序，这个设置是单纯的setter
        productImageService.setFirstProdutImagesOnOrderItems(orderItems);
        //返回的OrderItems中的product上就会有上一部设置的预览图
        session.setAttribute("ois", orderItems);

        Map<String,Object> map = new HashMap<>();
        map.put("orderItems", orderItems);
        map.put("total", total);
        return ResultUtil.success(map);

    }


}
