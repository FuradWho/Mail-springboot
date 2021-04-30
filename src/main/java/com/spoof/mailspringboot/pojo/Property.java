package com.spoof.mailspringboot.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "handler" })
public class Property {

    private int PropertyId;
    private String PropertyName;
    private Category PropertyCategory;

    public int getPropertyId() {
        return PropertyId;
    }

    public void setPropertyId(int propertyId) {
        PropertyId = propertyId;
    }

    public String getPropertyName() {
        return PropertyName;
    }

    public void setPropertyName(String propertyName) {
        PropertyName = propertyName;
    }

    public Category getPropertyCategory() {
        return PropertyCategory;
    }

    public void setPropertyCategory(Category propertyCategory) {
        PropertyCategory = propertyCategory;
    }

    @Override
    public String toString() {
        return "Property{" +
                "PropertyId=" + PropertyId +
                ", PropertyName='" + PropertyName + '\'' +
                ", PropertyCategory=" + PropertyCategory +
                '}';
    }
}
