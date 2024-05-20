package com.io.dropwizardhibernate.convertor;

import static java.util.Collections.emptyList;

import com.io.dropwizardhibernate.api.Product;
import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.common.ContextUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataConvertor {

    public static Map<String, Object> buildSuccessResponse (Long result) {
        Map<String, Object> successMap = new HashMap<>();
        successMap.put("success", true);
        successMap.put("results", result);

        return successMap;
    }

    public static Map<String, Object> buildErrorResponse(Long result) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("success", false);
        errorMap.put("reasons", String.format("The requested id %s doesn't exist",result.toString()));

        return errorMap;
    }

    public static Product productEntityToResponse(com.io.dropwizardhibernate.core.Product product) {
        if (product == null) {
            return Product.builder().build();
        }

        return Product.builder().id(product.getId())
                .productName(product.getProductName())
                .sku(product.getSku())
                .effectEndDate(product.getEffectEndDate())
                .effectStartDate(product.getEffectStartDate())
                .category(product.getCategory() != null ? product.getCategory().getCategoryName() : null)
                .description(product.getDescription())
                .productNumber(product.getProductNumber())
                .build();
    }

    public static List<Product> productEntitiesToResponse(List<com.io.dropwizardhibernate.core.Product> products) {
        if (products == null || products.isEmpty()) {
            return emptyList();
        }
        return products
                .stream()
                .map(DataConvertor::productEntityToResponse)
                .collect(Collectors.toList());
    }

    public static com.io.dropwizardhibernate.core.Product productRequestToEntity(ProductRequest product) {
        if (product == null) {
            return com.io.dropwizardhibernate.core.Product.builder().build();
        }

        return com.io.dropwizardhibernate.core.Product.builder()
                .productName(product.getProductName())
                .sku(product.getSku())
                .effectEndDate(product.getEffectEndDate())
                .effectStartDate(product.getEffectStartDate())
                .description(product.getDescription())
                .productNumber(product.getProductNumber())
                .category(product.getCategory() != null ?
                        com.io.dropwizardhibernate.core.ProductCategory.nameToValueMap.get(product.getCategory()) : null)
                .tenantId(ContextUtils.getRequestContext().getTenantId())
                .createdBy(ContextUtils.getRequestContext().getUserId())
                .updatedBy(ContextUtils.getRequestContext().getUserId())
                .createdOn(Timestamp.valueOf(LocalDateTime.now()))
                .updatedOn(Timestamp.valueOf(LocalDateTime.now()))
                .build();
    }
}
