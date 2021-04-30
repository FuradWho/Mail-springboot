package com.spoof.mailspringboot.comparator;
//价格比较器
//把价格低的放前面


import com.spoof.mailspringboot.pojo.Product;

import java.util.Comparator;

public class ProductPriceComparator implements Comparator<Product> {

    @Override
    public int compare(Product p1, Product p2) {
        return (int) (p1.getProductPromotePrice()-p2.getProductPromotePrice());
    }

}