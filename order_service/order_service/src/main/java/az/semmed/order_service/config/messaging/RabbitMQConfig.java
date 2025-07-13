package az.semmed.order_service.config.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.exchanges.internal}")
    private String INTERNAL_EXCHANGE;

    @Value("${rabbitmq.routing-keys.order-placed}")
    private String ORDER_PLACED_ROUTING_KEY;

    @Value("${rabbitmq.queue.notification}")
    private String NOTIFICATION_QUEUE;

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(INTERNAL_EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding orderPlacedBinding() {
        return BindingBuilder
                .bind(notificationQueue())
                .to(orderExchange())
                .with(ORDER_PLACED_ROUTING_KEY);

    }

    public String getInternalExchange() {
        return INTERNAL_EXCHANGE;
    }

    public String getOrderPlacedRoutingKey() {
        return ORDER_PLACED_ROUTING_KEY;
    }

    public String getNotificationQueue() {
        return NOTIFICATION_QUEUE;
    }
}
