package com.spoof.mailspringboot.service;

import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.Property;
import com.spoof.mailspringboot.pojo.PropertyValue;

import java.util.List;

public interface PropertyValueService {

    List<PropertyValue> getPropertyValueListByProduct(Product product);

    PropertyValue getByPropertyAndProduct(Product product, Property property);

}
