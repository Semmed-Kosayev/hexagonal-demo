package az.semmed.amqpcore;

public final class RabbitMQConstants {

    private RabbitMQConstants() {}

    public static final String ORDER_EXCHANGE = "order.exchange";
    public static final String ORDER_PLACED_ROUTING_KEY = "order.placed";
    public static final String NOTIFICATION_QUEUE = "notification.queue";
}
