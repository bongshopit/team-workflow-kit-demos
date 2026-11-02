package com.lego.order;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.WebApplicationException;
import java.util.function.Supplier;

/** InventoryClient that calls inventory-service over HTTP. Errors keep the upstream status. */
public class RestInventoryClient implements InventoryClient {
    private final InventoryApi api;

    public RestInventoryClient(InventoryApi api) {
        this.api = api;
    }

    @Override
    public int reserve(String sku, int quantity) {
        return call(() -> api.reserve(sku, new InventoryApi.Quantity(quantity)).available());
    }

    @Override
    public int release(String sku, int quantity) {
        return call(() -> api.release(sku, new InventoryApi.Quantity(quantity)).available());
    }

    private static int call(Supplier<Integer> request) {
        try {
            return request.get();
        } catch (WebApplicationException e) {
            int status = e.getResponse().getStatus();
            throw new ApiException(status, "inventory-service answered " + status);
        } catch (ProcessingException e) {
            throw new ApiException(503, "inventory-service is not reachable");
        }
    }
}
