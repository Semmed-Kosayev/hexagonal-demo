package az.semmed.notificationservice.application.config;

import az.semmed.amqpcore.RabbitMQConstants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationRabbitConfig {

    @Bean
    public TopicExchange orderExchange() {
        return new TopicExchange(RabbitMQConstants.ORDER_EXCHANGE);
    }

    @Bean
    public Queue notificationQueue() {
        return new Queue(RabbitMQConstants.NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding notificationBinding(TopicExchange topicExchange, Queue notificationQueue) {
        return BindingBuilder
                .bind(notificationQueue)
                .to(topicExchange)
                .with(RabbitMQConstants.ORDER_PLACED_ROUTING_KEY);
    }
}
