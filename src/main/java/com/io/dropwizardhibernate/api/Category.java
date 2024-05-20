package com.io.dropwizardhibernate.api;

public enum Category {

    BASE(0, "Base Products"),
    ADD_ON(1, "Add On Services"),
    OTHER(2, "Miscellaneous Products");
    private final Integer value;
    private final String categoryName;

    Category(Integer value, String categoryName) {
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
