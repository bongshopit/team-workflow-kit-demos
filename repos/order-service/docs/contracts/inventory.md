# Contract: order-service uses inventory-service

`com.lego.order.InventoryClient` is the only place order-service talks to inventory-service.
It mirrors `inventory-service/docs/api.md`. A change here needs a matching change there.

| Client method | inventory-service operation |
|---|---|
| `available(sku)` | `get(sku).available` |

Over HTTP (`InventoryApi`, base URL `quarkus.rest-client.inventory.url`):

| Client method | Route |
|---|---|
| `reserve(sku, quantity)` | `POST /products/{sku}/reserve` body `{quantity}` |
| `release(sku, quantity)` | `POST /products/{sku}/release` body `{quantity}` |

An error from inventory-service keeps its status (404 unknown sku, 409 insufficient stock). An unreachable inventory-service is 503.
