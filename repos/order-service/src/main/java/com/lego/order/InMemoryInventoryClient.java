package com.lego.order;

import java.util.HashMap;
import java.util.Map;

/** Stand-in for inventory-service: every SKU starts with 100 in stock. For trying order-service alone (inventory.mode=stub). */
public class InMemoryInventoryClient implements InventoryClient {
    private final Map<String, Integer> stock = new HashMap<>();

    @Override
    public synchronized int reserve(String sku, int quantity) {
        if (quantity <= 0) throw new ApiException(400, "quantity must be positive");
        int available = stock.getOrDefault(sku, 100);
        if (quantity > available) throw new ApiException(409, "insufficient stock for " + sku);
        stock.put(sku, available - quantity);
        return available - quantity;
    }

    @Override
    public synchronized int release(String sku, int quantity) {
        if (quantity <= 0) throw new ApiException(400, "quantity must be positive");
        int available = stock.getOrDefault(sku, 100) + quantity;
        stock.put(sku, available);
        return available;
    }
}
