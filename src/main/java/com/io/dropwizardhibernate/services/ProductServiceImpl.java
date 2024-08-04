package com.io.dropwizardhibernate.services;

import com.io.dropwizardhibernate.ProductMapper;
import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.core.Product;
import com.io.dropwizardhibernate.db.ProductDAO;
import com.io.dropwizardhibernate.error.ErrorCode;
import com.io.dropwizardhibernate.error.ErrorMessage;
import com.io.dropwizardhibernate.error.ErrorType;
import com.io.dropwizardhibernate.exception.ApiException;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public Product createProduct(Product product) {
        return productDAO.saveOrUpdate(product);
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productDAO.findById(id);
    }


    @Override
    public List<Product> getProducts() {
        return productDAO.getProducts();
    }

    @Override
    public Product updateProduct(Long id, ProductRequest productRequest, boolean isPatch) {
        if (isPatch) {
            return updateProductPatch(id, productRequest);
        }
        return updateProduct(id, productRequest);
    }

    private Product updateProduct(Long id, ProductRequest productRequest) {
        Product product = productDAO.findById(id)
                .orElseThrow(() ->
                        new ApiException(ErrorType.bad_request,
                                List.of(ErrorMessage
                                        .buildWithMessage(ErrorCode.invalid_value,
                                                "Product not found", null, null))));
        ProductMapper productMapper = ProductMapper.INSTANCE;
        productMapper.apiToEntityExisting(productRequest, product);
        return productDAO.update(product);
    }

    private Product updateProductPatch(Long id, ProductRequest productRequest) {
        Product product = productDAO.findById(id)
                .orElseThrow(() ->
                        new ApiException(ErrorType.bad_request,
                        List.of(ErrorMessage
                                .buildWithMessage(ErrorCode.invalid_value,
                        "Product not found", null, null))));
        ProductMapper productMapper = ProductMapper.INSTANCE;
        Product requestEntity = productMapper.apiToEntity(productRequest);
        requestEntity.setId(id);
        productMapper.mergeOldAndSavingEntity(requestEntity, product);
        return productDAO.update(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDAO.deleteById(id);
    }
}
