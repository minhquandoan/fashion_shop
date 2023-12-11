package com.minhquan;

import com.minhquan.dto.ProductDto;
import com.minhquan.service.ProductService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path(value = "/v1/product")
public class ProductController {
    @Inject
    ProductService service;

    @GET
    @Path(value = "/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createProduct(ProductDto productDto) {
        final var result = service.createProduct(productDto);
        return Response.ok(result).build();
    }
}
