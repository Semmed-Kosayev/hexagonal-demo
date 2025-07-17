package az.semmed.notificationservice.support.mapper;

import az.semmed.notificationservice.adapter.out.NotificationEntity;
import az.semmed.notificationservice.domain.model.Notification;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class NotificationMapper {

    public NotificationEntity toNotificationEntity(Notification notification) {
        return NotificationEntity.builder()
                .id(UUID.randomUUID())
                .orderId(notification.getOrderId())
                .message(notification.getMessage())
                .build();
    }
}
