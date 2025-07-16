package az.semmed.order_service.adapter.in.web.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record PlaceOrderRequest(List<Item> items) {
    public record Item(UUID productId, int quantity, BigDecimal price) {}
}
