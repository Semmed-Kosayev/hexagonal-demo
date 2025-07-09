package az.semmed.order_service.domain.port.out;

import az.semmed.order_service.domain.model.Order;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {

    void save(Order order);

    Optional<Order> findById(UUID id);

    List<Order> findAll();

    void deleteById(UUID id);
}
