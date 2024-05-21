package api;

import static org.junit.Assert.assertEquals;

import com.io.dropwizardhibernate.api.Product;
import com.io.dropwizardhibernate.api.ProductRequest;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.junit.jupiter.api.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

public class ApiObjectTest {

    @Test
    void testProductsAPI() {
        ProductRequest productRequest = ProductRequest
                .builder()
                .productName("test")
                .description("PRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdf")
                .build();

        Validator validator = javax.validation.Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<ProductRequest>> violations =
                validator.validate(productRequest);

        ConstraintViolation<ProductRequest> violation = violations.iterator().next();
        System.out.println(violation.getMessage());
        assertEquals("Invalid value: 'PRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdf'. Description cannot be more than 255 characters", violation.getMessage());

    }
}
