package az.semmed.order_service.config.messaging;

import az.semmed.order_service.domain.event.OrderPlacedEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RabbitOrderEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final RabbitMQConfig rabbitMQConfig;

    public void publish(OrderPlacedEvent event) {
        try {
            String json = objectMapper.writeValueAsString(event);
            rabbitTemplate.convertAndSend(
                    rabbitMQConfig.getInternalExchange(),
                    rabbitMQConfig.getOrderPlacedRoutingKey(),
                    json
            );
            System.out.println("✅ Published OrderPlacedEvent to RabbitMQ");
        } catch (JsonProcessingException e) {
            System.err.println("❌ Failed to publish OrderPlacedEvent: " + e.getMessage());
        }
    }
}
