package az.semmed.order_service.application.service;

import az.semmed.order_service.application.dto.OrderItemCommand;
import az.semmed.order_service.domain.model.Order;
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

    public OrderCommandService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    public void cancel(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.cancel();
        orderRepository.save(order);
    }

    @Override
    public UUID place(List<OrderItemCommand> items) {
        UUID orderId = UUID.randomUUID();
        Order order = new Order(orderId);

        items.forEach(i ->
                order.addItem(i.productId(), i.price(), i.quantity())
        );

        orderRepository.save(order);
        return orderId;
    }

    @Override
    public void ship(UUID orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.markAsShipped();
        orderRepository.save(order);
    }
}
