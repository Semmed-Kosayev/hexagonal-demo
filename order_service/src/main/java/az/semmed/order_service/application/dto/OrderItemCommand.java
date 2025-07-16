package az.semmed.order_service.application.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemCommand(UUID productId, int quantity, BigDecimal price) {
}
