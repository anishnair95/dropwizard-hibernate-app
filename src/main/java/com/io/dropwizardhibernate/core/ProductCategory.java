package com.io.dropwizardhibernate.core;

import static java.util.function.Function.identity;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public enum ProductCategory {

    BASE(0, "Base Products"),
    ADD_ON(1, "Add On Services"),
    OTHER(2, "Miscellaneous Products");
    private final Integer value;
    private final String categoryName;

    public static Map<String, ProductCategory> nameToValueMap = Arrays.stream(ProductCategory.values())
            .collect(Collectors.toMap(ProductCategory::getCategoryName, identity()));

    ProductCategory(Integer value, String categoryName) {
        this.value = value;
        this.categoryName = categoryName;
    }


    public Integer getValue() {
        return value;
    }

    public String getCategoryName() {
        return categoryName;
    }
}
