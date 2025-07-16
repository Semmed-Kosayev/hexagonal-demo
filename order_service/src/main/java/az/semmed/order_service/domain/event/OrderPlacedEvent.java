package az.semmed.order_service.domain.event;

import java.util.List;
import java.util.UUID;

public class OrderPlacedEvent {

    private final UUID orderId;
    private final List<UUID> productIds;

    public OrderPlacedEvent(UUID orderId, List<UUID> productIds) {
        this.orderId = orderId;
        this.productIds = productIds;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<UUID> getProductIds() {
        return productIds;
    }
}
