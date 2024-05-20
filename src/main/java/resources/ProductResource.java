package resources;

import static com.io.dropwizardhibernate.convertor.DataConvertor.buildErrorResponse;
import static com.io.dropwizardhibernate.convertor.DataConvertor.productEntityToResponse;

import com.io.dropwizardhibernate.api.Product;
import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.convertor.DataConvertor;
import com.io.dropwizardhibernate.db.ProductDAO;
import com.io.dropwizardhibernate.services.ProductService;
import io.dropwizard.hibernate.UnitOfWork;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GET
    @UnitOfWork
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProductById(@PathParam("id") Long id) {
        return productService.getProductById(id).map(DataConvertor::productEntityToResponse)
                .map(product -> Response.ok().entity(product).build())
                .orElseGet(() -> Response.status(Response.Status.NOT_FOUND)
                        .entity(buildErrorResponse(id)).build());
    }

    // create product api
    @POST
    @UnitOfWork
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(@NotNull @Valid ProductRequest product) {
//        return DataConvertor.productEntityToResponse(productService.createProduct(product));
        // could be like below if we keep Response object in return type
        return Response.ok()
                .entity(DataConvertor.productEntityToResponse(productService.createProduct(DataConvertor.productRequestToEntity(product))))
                .build();

    }

    @GET
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts() {
        return DataConvertor.productEntitiesToResponse(productService.getProducts());
    }

}
