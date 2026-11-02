# Contract: order-service uses inventory-service

`com.lego.order.InventoryClient` is the only place order-service talks to inventory-service.
It mirrors `inventory-service/docs/api.md`. A change here needs a matching change there.

| Client method | inventory-service operation |
|---|---|
| `available(sku)` | `get(sku).available` |
