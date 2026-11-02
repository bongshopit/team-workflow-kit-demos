package com.lego.order;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

/** The HTTP calls to inventory-service. Mirrors inventory-service/docs/api.md. Used only by RestInventoryClient. */
@RegisterRestClient(configKey = "inventory")
@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface InventoryApi {

    record Quantity(int quantity) {
    }

    record Stock(String sku, String name, int available) {
    }

    @POST
    @Path("/{sku}/reserve")
    Stock reserve(@PathParam("sku") String sku, Quantity body);

    @POST
    @Path("/{sku}/release")
    Stock release(@PathParam("sku") String sku, Quantity body);
}
