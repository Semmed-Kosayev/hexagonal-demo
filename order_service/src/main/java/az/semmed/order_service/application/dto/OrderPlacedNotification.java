package az.semmed.order_service.application.dto;

import java.util.UUID;

public record OrderPlacedNotification(UUID orderId) {
}
