# Order service API

Service class: `com.lego.order.OrderService`. Errors are `ApiException` with an HTTP status.

## create(sku, quantity)
- 200: the order (`id`, `sku`, `quantity`, `status` = open)
- 400: blank sku, quantity not positive
- 409: not enough stock

## get(id)
- 200: the order
- 404: unknown order

## cancel(id)
- 200: the cancelled order
- 404: unknown order
- 409: already cancelled
