package resources;

import com.io.dropwizardhibernate.api.ErrorResponse;
import com.io.dropwizardhibernate.error.ErrorCode;
import com.io.dropwizardhibernate.error.ErrorMessage;
import com.io.dropwizardhibernate.error.ErrorType;
import com.io.dropwizardhibernate.exception.ApiException;

import java.util.List;

import java.util.function.Supplier;

import javax.ws.rs.core.Response;

public class BaseResource {
    public static <T,R> Response doRest(Supplier<R> producer) {
        // do something
        Either<T,R> response = getResponse(producer);
        if (response.isRight()) {
            return Response.ok(response.right()).build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(response.left())
                    .build();
        }
    }

    private static <T,R> Either<T,R>  getResponse(Supplier<R> producer) {
        try {
            R obj = producer.get();
            return new Either(null, obj);
        } catch (ApiException ex) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .type(ex.getType())
                    .errorMessages(ex.getErrorMessages())
                    .build();
            return new Either(errorResponse, null);
        } catch (Exception ex) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .type(ErrorType.bad_request)
                    .errorMessages(List.of(ErrorMessage.builder().code(ErrorCode.unexpected_error)
                            .message(ex.getMessage())
                            .build()))
                    .build();
            return new Either(errorResponse, null);
        }
    }
}


class Either<T, R> {
    private T left;
    private R right;

    public Either(T left, R right) {
        this.left = left;
        this.right = right;
    }

    public T left() {
        return left;
    }

    public R right() {
        return right;
    }

    public boolean isLeft() {
        return left != null;
    }

    public boolean isRight() {
        return right != null;
    }
}