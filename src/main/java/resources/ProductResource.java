package resources;

import static com.io.dropwizardhibernate.convertor.DataConvertor.buildErrorResponse;
import static com.io.dropwizardhibernate.convertor.DataConvertor.productEntityToResponse;

import com.io.dropwizardhibernate.api.Product;
import com.io.dropwizardhibernate.api.ProductRequest;
import com.io.dropwizardhibernate.convertor.DataConvertor;
import com.io.dropwizardhibernate.services.ProductService;
import io.dropwizard.hibernate.UnitOfWork;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/product")
public class ProductResource extends BaseResource {

    private final ProductService productService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductResource.class);
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
    @Produces(MediaType.APPLICATION_JSON) // without @Valid annotation the hibernate validator doesn't work
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

    @PATCH
    @Path("/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProductPatch(@PathParam("id") Long id, @Valid ProductRequest request) {
        // update the product
        return doRest(() -> {
            LOGGER.info("Updating product with id: {}", id);
            return DataConvertor.productEntityToResponse(productService.updateProduct(id, request, true));
        });
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") Long id, @Valid ProductRequest request) {
        // update the product
        return doRest(() -> {
            LOGGER.info("Updating product with id: {}", id);
            return Response.ok()
                    .entity(DataConvertor.productEntityToResponse(productService.updateProduct(id, request, false)))
                    .build();
        });
    }


    @DELETE
    @UnitOfWork
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response deleteProduct(@PathParam("id") Long id) {
        productService.deleteProduct(id);
        return Response.ok().entity(Map.of("success", true)).build();
    }

}
