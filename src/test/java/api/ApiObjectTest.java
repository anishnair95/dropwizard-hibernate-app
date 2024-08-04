package api;

import static org.junit.Assert.assertEquals;

import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.error.ErrorCode;
import com.io.dropwizardhibernate.error.ErrorMessage;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
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

        List<ErrorMessage> errorMessages = checkViolations(productRequest);
        ErrorMessage violation = errorMessages.get(0);
        System.out.println(violation.getMessage());
        assertEquals("Invalid value: 'PRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdfPRP_asdfasdf'. Description cannot be more than '20' characters", violation.getMessage());

    }

    public <T> List<com.io.dropwizardhibernate.error.ErrorMessage> checkViolations(T object) {
        Validator validator = javax.validation.Validation.byDefaultProvider()
                .configure()
                .messageInterpolator(new ResourceBundleMessageInterpolator())
                .buildValidatorFactory()
                .getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        List<com.io.dropwizardhibernate.error.ErrorMessage> errors = new ArrayList<>();
        violations.forEach(v -> {
            String paramName = v.getPropertyPath().toString();
            errors.add(com.io.dropwizardhibernate.error.ErrorMessage.buildWithMessage(ErrorCode.invalid_parameter, v.getMessage(), paramName));
        });

        return errors;
    }

    @Test
    public void testRun() {
        int year = 2024;
        int month = 05;
        int day = 06;

        String testInput = String.format("%02d/%02d/%d", month, day, year);
        System.out.println(testInput);

    }
}
