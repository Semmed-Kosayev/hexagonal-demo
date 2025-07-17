package az.semmed.order_service.application.service;

import az.semmed.amqpcore.RabbitMQConstants;
import az.semmed.amqpcore.RabbitMQMessagePublisher;
import az.semmed.amqpcore.dto.OrderPlacedNotification;
import az.semmed.order_service.application.dto.OrderItemCommand;
import az.semmed.order_service.domain.event.OrderPlacedEvent;
import az.semmed.order_service.domain.model.Order;
import az.semmed.order_service.domain.model.OrderItem;
import az.semmed.order_service.domain.port.in.CancelOrderUseCase;
import az.semmed.order_service.domain.port.in.PlaceOrderUseCase;
import az.semmed.order_service.domain.port.in.ShipOrderUseCase;
import az.semmed.order_service.domain.port.out.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderCommandService implements
        PlaceOrderUseCase,
        CancelOrderUseCase,
        ShipOrderUseCase {

    private final OrderRepository orderRepository;
    private final RabbitMQMessagePublisher publisher;

    public OrderCommandService(OrderRepository orderRepository, RabbitMQMessagePublisher publisher) {
        this.orderRepository = orderRepository;
        this.publisher = publisher;
    }

    @Override
    public void cancel(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.cancel();
        orderRepository.save(order);
    }

    @Override
    public UUID place(List<OrderItemCommand> itemsCommand) {
        List<OrderItem> items = itemsCommand.stream()
                .map(cmd -> new OrderItem(
                        cmd.productId(),
                        cmd.quantity(),
                        cmd.price())
                )
                .toList();

        Order order = Order.place(items);
        orderRepository.save(order);

        order.getDomainEvents().forEach(event -> {
            if (event instanceof OrderPlacedEvent orderPlacedEvent) {
                publisher.publish(
                        new OrderPlacedNotification(orderPlacedEvent.getOrderId()),
                        RabbitMQConstants.ORDER_EXCHANGE,
                        RabbitMQConstants.ORDER_PLACED_ROUTING_KEY
                );
            }
        });

        return order.getId();
    }

    @Override
    public void ship(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.markAsShipped();
        orderRepository.save(order);
    }
}
