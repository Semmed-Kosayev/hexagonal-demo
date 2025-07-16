package az.semmed.order_service.adapter.out;

import az.semmed.order_service.domain.model.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    // One order -> many items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItemEntity> items = new ArrayList<>();

    protected OrderEntity() { }           // JPA

    public OrderEntity(UUID id, OrderStatus status) {
        this.id = id;
        this.status = status;
    }

    public void addItem(OrderItemEntity item) {
        item.setOrder(this);
        items.add(item);
    }

    public UUID getId() {
        return id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public List<OrderItemEntity> getItems() {
        return items;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
