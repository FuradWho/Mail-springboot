package com.spoof.mailspringboot.service.Impl;


import com.spoof.mailspringboot.mapper.PropertyValueMapper;
import com.spoof.mailspringboot.pojo.Product;
import com.spoof.mailspringboot.pojo.Property;
import com.spoof.mailspringboot.pojo.PropertyValue;
import com.spoof.mailspringboot.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyValueServiceImpl implements PropertyValueService {

    @Autowired
    private PropertyValueMapper propertyValueMapper;

    @Override
    public List<PropertyValue> getPropertyValueListByProduct(Product product) {
        return propertyValueMapper.findByProductOrderByIdDesc(product);
    }

    @Override
    public PropertyValue getByPropertyAndProduct(Product product, Property property) {
        return propertyValueMapper.getByPropertyAndProduct(property,product);
    }
}
