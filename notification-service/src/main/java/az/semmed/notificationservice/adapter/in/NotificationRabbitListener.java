package az.semmed.notificationservice.adapter.in;

import az.semmed.amqpcore.RabbitMQConstants;
import az.semmed.amqpcore.dto.OrderPlacedNotification;
import az.semmed.notificationservice.domain.port.in.OrderPlaced;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationRabbitListener {

    private final OrderPlaced orderPlaced;

    @RabbitListener(queues = RabbitMQConstants.NOTIFICATION_QUEUE)
    public void consumeOrderPlacedEvent(OrderPlacedNotification notification) {
        orderPlaced.orderPlaced(notification.orderId());
    }
}
