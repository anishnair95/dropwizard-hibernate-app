package resources;

import com.io.dropwizardhibernate.api.Saying;
import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.caching.CacheControl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/hello-world")

// providing and consuming application/json representations
@Produces(MediaType.APPLICATION_JSON)
public class TestResource {

    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public TestResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(TestResource.class);


    //GET method
    @GET //get endpoint
    // the method execution time will be recorded
    @Timed(name = "get-requests-timed")
    //Caching
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {

        LOGGER.info("Inside sayHello");
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
