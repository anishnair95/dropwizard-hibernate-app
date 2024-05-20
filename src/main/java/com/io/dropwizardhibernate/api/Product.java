package com.io.dropwizardhibernate.api;

import com.io.dropwizardhibernate.core.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Product {

    private Long id;
    private String productName;
    private String description;
    private String sku;
    private Date effectStartDate;
    private Date effectEndDate;
    private String category;
    private String productNumber;
}
