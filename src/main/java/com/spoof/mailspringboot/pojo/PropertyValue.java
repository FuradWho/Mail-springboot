package com.spoof.mailspringboot.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class PropertyValue {

    private int PropertyValueId;
    private Product PropertyValueProduct;
    private Property PropertyValueProperty;
    private String value;

    public int getPropertyValueId() {
        return PropertyValueId;
    }

    public void setPropertyValueId(int propertyValueId) {
        PropertyValueId = propertyValueId;
    }

    public Product getPropertyValueProduct() {
        return PropertyValueProduct;
    }

    public void setPropertyValueProduct(Product propertyValueProduct) {
        PropertyValueProduct = propertyValueProduct;
    }

    public Property getPropertyValueProperty() {
        return PropertyValueProperty;
    }

    public void setPropertyValueProperty(Property propertyValueProperty) {
        PropertyValueProperty = propertyValueProperty;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "PropertyValue{" +
                "PropertyValueId=" + PropertyValueId +
                ", PropertyValueProduct=" + PropertyValueProduct +
                ", PropertyValueProperty=" + PropertyValueProperty +
                ", value='" + value + '\'' +
                '}';
    }
}
