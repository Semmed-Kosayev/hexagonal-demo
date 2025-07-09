package az.semmed.order_service.support.mapper;

import az.semmed.order_service.adapter.out.OrderEntity;
import az.semmed.order_service.adapter.out.OrderItemEntity;
import az.semmed.order_service.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderEntity toEntity(Order order) {
        OrderEntity entity = new OrderEntity(order.getId(), order.getStatus());

        order.getItems().forEach(i ->
                entity.addItem(new OrderItemEntity(
                        i.getProductId(),
                        i.getQuantity(),
                        i.getPrice())
                )
        );

        return entity;
    }

    public Order toOrder(OrderEntity entity) {
        Order order = new Order(entity.getId());

        switch (entity.getStatus()) {
            case SHIPPED -> order.markAsShipped();
            case CANCELLED -> order.cancel();
            default -> {/*CREATED*/}
        }

        entity.getItems().forEach(i ->
                order.addItem(i.getProductId(), i.getPrice(), i.getQuantity())
        );
        return order;
    }
}
