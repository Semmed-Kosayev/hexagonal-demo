package az.semmed.order_service.domain.model;

import az.semmed.order_service.domain.event.OrderPlacedEvent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {

    private final UUID id;
    private final List<OrderItem> items;
    private OrderStatus status;
    private final List<Object> domainEvents = new ArrayList<>();

    public Order(UUID id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.status = OrderStatus.CREATED;
    }

    public void addItem(UUID productId, BigDecimal price, int quantity) {
        if (!status.equals(OrderStatus.CREATED)) {
            throw new IllegalStateException("Cannot modify this order");
        }
        items.add(new OrderItem(productId, quantity, price));
    }

    public void cancel() {
        if (!status.equals(OrderStatus.CREATED)) {
            throw new IllegalStateException("Only CREATED orders can be cancelled");
        }

        this.status = OrderStatus.CANCELLED;
    }

    public void markAsShipped() {
        if (!status.equals(OrderStatus.CREATED)) {
            throw new IllegalStateException("Only CREATED orders can be marked as shipped");
        }

        this.status = OrderStatus.SHIPPED;
    }

    public BigDecimal getTotalPrice() {
        return items.stream()
                .map(OrderItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void registerEvent(Object event) {
        domainEvents.add(event);
    }

    public UUID getId() {
        return id;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<Object> getDomainEvents() {
        return Collections.unmodifiableList(domainEvents);
    }

    public static Order place(List<OrderItem> items) {
        UUID orderId = UUID.randomUUID();
        Order order = new Order(orderId);

        List<UUID> productIds = new ArrayList<>();

        items.forEach(i ->
                {
                    order.addItem(i.getProductId(), i.getPrice(), i.getQuantity());
                    productIds.add(i.getProductId());
                }
        );

        order.registerEvent(new OrderPlacedEvent(orderId, productIds));

        return order;
    }
}
