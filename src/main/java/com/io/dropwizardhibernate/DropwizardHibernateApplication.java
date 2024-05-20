package com.io.dropwizardhibernate;

import com.io.dropwizardhibernate.core.Product;
import com.io.dropwizardhibernate.db.ProductDAO;
import com.io.dropwizardhibernate.db.RepositoryImpl;
import com.io.dropwizardhibernate.filter.RequestContextFilter;
import com.io.dropwizardhibernate.health.HealthCheckService;
import com.io.dropwizardhibernate.services.ProductService;
import com.io.dropwizardhibernate.services.ProductServiceImpl;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.migrations.MigrationsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import resources.ProductResource;
import resources.TestResource;

import javax.servlet.ServletRegistration;

public class DropwizardHibernateApplication extends Application<DropwizardHibernateConfiguration> {

    private final HibernateBundle<DropwizardHibernateConfiguration> hibernateBundle =
            new HibernateBundle<DropwizardHibernateConfiguration>(RepositoryImpl.class, Product.class) {
                @Override
                public DataSourceFactory getDataSourceFactory(DropwizardHibernateConfiguration configuration) {
                    return configuration.getDataSourceFactory();
                }
            };

    public static void main(final String[] args) throws Exception {
        new DropwizardHibernateApplication().run(args);
    }

    @Override
    public String getName() {
        return "DropwizardHibernate";
    }

    @Override
    public void initialize(final Bootstrap<DropwizardHibernateConfiguration> bootstrap) {
        // migration bundle
        //bundle for liquibase migration
        bootstrap.addBundle(new MigrationsBundle<DropwizardHibernateConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(DropwizardHibernateConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }
        });
        //bundle for hibernate config

        // hibernate bundle
        bootstrap.addBundle(hibernateBundle);

    }

    @Override
    public void run(final DropwizardHibernateConfiguration configuration,
                    final Environment environment) {

        // db resources
        final ProductDAO productDAO = new ProductDAO(hibernateBundle.getSessionFactory());
        // resources
        final TestResource testResource = new TestResource(configuration.getTemplate(), configuration.getDefaultName());
        final HealthCheckService healthCheckService = new HealthCheckService(configuration.getTemplate());
        final ProductService productService = new ProductServiceImpl(productDAO);
        final ProductResource productResource = new ProductResource(productService);

        //registering h2
        // Register the H2 console servlet
        final ServletRegistration.Dynamic h2Servlet = environment.servlets()
                .addServlet("H2Console", new org.h2.server.web.WebServlet());

        // Configure the H2 console servlet
        h2Servlet.addMapping("/h2/*");
        h2Servlet.setInitParameter("webAllowOthers", "true");

        // register all services
        environment.healthChecks().register("health", healthCheckService);
        environment.jersey().register(testResource);
        environment.jersey().register(productResource);

        registerFilters(environment);


    }

    private void registerFilters(Environment environment) {
        environment.jersey().register(new RequestContextFilter());
    }
}
