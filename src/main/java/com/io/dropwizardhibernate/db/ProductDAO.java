package com.io.dropwizardhibernate.db;

import com.io.dropwizardhibernate.common.ContextUtils;
import com.io.dropwizardhibernate.common.RequestContext;
import com.io.dropwizardhibernate.core.Product;
import org.hibernate.SessionFactory;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


public class ProductDAO extends RepositoryImpl<Product, Long> {

    public ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Query to get all products
     * Using criteria query to get all products
     * @return
     */
    public List<Product> getProducts() {
        RequestContext requestContext = ContextUtils.getRequestContext();
        CriteriaBuilder criteriaBuilder = currentSession().getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaQuery();
        Root<Product> root = criteriaQuery.from(Product.class);
        Predicate predicate = criteriaBuilder.equal(root.get("tenantId"), requestContext.getTenantId());
        criteriaQuery.where(predicate);
        return list(criteriaQuery);
    }
}
