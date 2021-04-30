package com.spoof.mailspringboot.service.Impl;

import com.spoof.mailspringboot.mapper.OrderItemMapper;
import com.spoof.mailspringboot.mapper.ProductMapper;
import com.spoof.mailspringboot.pojo.Category;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.service.OrderItemService;
import com.spoof.mailspringboot.service.ProductImageService;
import com.spoof.mailspringboot.service.ProductService;
import com.spoof.mailspringboot.service.ReviewService;
import com.spoof.mailspringboot.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 13375
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;
    @Autowired
    private OrderItemService orderItemService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ProductImageService productImageService;

    @Override
    public List<Product> findByCategoryOrderById(Category category) {
        return productMapper.findByCategoryOrderById(category);
    }

    @Override
    public Product findByPid(int pid) {
        return productMapper.findByPid(pid);
    }

    @Override
    public void setSaleAndReviewNumber(Product product) {
        int saleCount = orderItemService.getSaleCount(product);
        product.setSaleCount(saleCount);

        int reviewCount = reviewService.countByProduct(product);
        product.setReviewCount(reviewCount);

    }

    @Override
    public void fill(List<Category> categorys) {
        for (Category category : categorys) {
            fill(category);
        }
    }

    @Override
    public void fill(Category category) {
        //springboot 的缓存机制是通过切面编程 aop来实现的。
        //从fill方法里直接调用 listByCategory 方法， aop 是拦截不到的，也就不会走缓存了。
        //所以要通过这种 绕一绕 的方式故意诱发 aop, 这样才会想我们期望的那样走redis缓存。
        ProductService productService = SpringContextUtil.getBean(ProductService.class);
        List<Product> products = listByCategory(category);
        productImageService.setFirstProdutImage(products);
        category.setProductList(products);
    }

    @Override
    public List<Product> listByCategory(Category category){
        return productMapper.findByCategoryOrderById(category);
    }
    //为多个分类填充推荐产品集合，即把分类下的产品集合，按照8个为一行，拆成多行，以利于后续页面上进行显示
    @Override
    public void fillByRow(List<Category> categorys) {
        int productNumberEachRow = 8;
        for (Category category : categorys) {//将List<Product> 进行分拆装入List<List<Product>>
            List<Product> products =  category.getProductList();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products) {
            setSaleAndReviewNumber(product);
        }
    }

    @Override
    public void setFirstProdutImage(Product product) {

    }

    //将List<Product>中的Product中的category置为Null，在前台中不通过分类对象获得商品信息也行
    @Override
    public void removeCategoryFromProduct(List<Category> categories){
        for(Category category:categories){
            removeCategoryFromProduct(category);
        }
    }

    @Override
    public void removeCategoryFromProduct(Category category){
        List<Product> products =  category.getProductList();
        if(products!=null){
            for(Product product:products){

                product.setProductCategory(null);
            }
        }
        //置productsByRow中的product中的category为空
       List<List<Product>> productsByRow = category.getProductsByRow();
        if(productsByRow !=null){
            for(List<Product> products1:productsByRow){
                for(Product product:products1){
                    product.setProductCategory(null);
                }
            }
        }

    }


}
