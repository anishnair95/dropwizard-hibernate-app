package com.io.dropwizardhibernate.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ProductRequest {

    @NotNull(message = "Product name cannot be null")
    @NotEmpty(message = "Product name cannot be empty")
    private String productName;
    @Size(max = 20, message = "Invalid value: '${validatedValue}'. Description cannot be more than 255 characters")
    private String description;
    @Size(max = 50, message = "cannot be more than 50 characters")
    private String sku;
    private Date effectStartDate;
    private Date effectEndDate;
    private String category;
    @Size(max = 50, message = "cannot be more than 50 characters")
    private String productNumber;
}
