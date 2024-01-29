package com.minhquan;

import com.minhquan.dto.ProductDto;
import com.minhquan.service.ProductService;

import io.quarkus.logging.Log;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path(value = "/v1/product")
public class ProductTransport {
    @Inject
    ProductService service;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductDto productDto) {
        final var result = service.createProduct(productDto);
        return Response.ok(result).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(@PathParam("id") String id, ProductDto newRecord) {
        final var result = this.service.updateProduct(id, newRecord);
        return Response.status(Status.OK).entity(result).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Response getProduct(@PathParam("id") 
                                String id) {
        Log.tracev("Retrieving product with ID {0}", id);
        return Response.ok().entity(this.service.getProduct(id)).build();                          
    }
}
