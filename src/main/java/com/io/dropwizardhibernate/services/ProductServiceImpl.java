package com.io.dropwizardhibernate.services;

import com.io.dropwizardhibernate.ProductMapper;
import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.core.Product;
import com.io.dropwizardhibernate.db.ProductDAO;

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
    public Product updateProduct(Long id, ProductRequest productRequest) {
        Product product = productDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
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
