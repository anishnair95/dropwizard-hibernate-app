package com.io.dropwizardhibernate.services;

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
}
