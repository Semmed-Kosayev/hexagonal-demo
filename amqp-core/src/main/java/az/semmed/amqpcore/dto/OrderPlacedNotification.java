package az.semmed.amqpcore.dto;

import java.util.UUID;

public record OrderPlacedNotification(UUID orderId) {
}
