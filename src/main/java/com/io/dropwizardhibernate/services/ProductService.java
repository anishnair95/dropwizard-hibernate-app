package com.io.dropwizardhibernate.services;

import com.io.dropwizardhibernate.core.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product createProduct(Product product);

    Optional<Product> getProductById(Long id);

    List<Product> getProducts();

//    Product updateProduct(Long id, ProductRequest productRequest);
//
//    void deleteProduct(Long id);
//
//    List<Product> getAllProducts();
//
//    List<Product> getProductsByCategory(String category);
//
//    List<Product> getProductsByTenantId(String tenantId);
//
//    List<Product> getProductsByTenantIdAndCategory(String tenantId, String category);
//
//    List<Product> getProductsByTenantIdAndSku(String tenantId, String sku);
//
//    List<Product> getProductsByTenantIdAndProductNumber(String tenantId, String productNumber);
//
//    List<Product> getProductsByTenantIdAndCategoryAndSku(String tenantId, String category, String sku);
//
//    List<Product> getProductsByTenantIdAndCategoryAndProductNumber(String tenantId, String category, String productNumber);
//
//    List<Product> getProductsByTenantIdAndSkuAndProductNumber(String tenantId, String sku, String productNumber);
//
//    List<Product> getProductsByTenantIdAndCategoryAndSkuAndProductNumber(String tenantId, String category, String sku, String productNumber);
//
//    List<Product> getProductsByTenantIdAndEffectStartDate(String tenantId, Date effectStartDate);
//
//    List<Product> getProductsByTenantIdAndEffectEndDate(String tenantId, Date effectEndDate);
//
//    List<Product> getProductsByTenantIdAndCategoryAndEffectStartDate(String tenantId, String category, Date effectStartDate);
//
//    List<Product> getProductsByTenantIdAndCategoryAndEffectEndDate(String tenantId, String category, Date effectEndDate);
//
//    List<Product> getProductsByTenantIdAndCategoryAndEffectStartDateAndEffectEndDate(String tenantId, String category, Date effectStartDate, Date effectEndDate);
//
//    List<Product> getProductsByTenantIdAndSkuAndEffectStartDate(String tenantId, String sku, Date effectStartDate);
//
//    List<Product> getProductsByTenantIdAndSkuAndEffectEndDate(String tenantId, String sku, Date effectEndDate);
//
//    List<Product> getProductsByTenantIdAndSkuAndEffectStartDateAndEffectEndDate(String tenantId, String sku, Date effectStartDate, Date effectEndDate);
//
//    List<Product> getProductsByTenantIdAndCategoryAndSkuAndEffectStartDate(String tenantId, String category, String sku, Date effectStartDate);
//
//    List<Product> getProductsByTenantId
}
