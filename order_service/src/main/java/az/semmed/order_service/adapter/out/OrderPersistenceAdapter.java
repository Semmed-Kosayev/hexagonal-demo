package az.semmed.order_service.adapter.out;

import az.semmed.order_service.domain.model.Order;
import az.semmed.order_service.domain.port.out.OrderRepository;
import az.semmed.order_service.support.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class OrderPersistenceAdapter implements OrderRepository {

    private final SpringDataOrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderPersistenceAdapter(SpringDataOrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(
                orderMapper.toEntity(order)
        );
    }

    @Override
    public Optional<Order> findById(UUID id) {
        return orderRepository.findById(id).map(orderMapper::toOrder);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::toOrder).toList();
    }

    @Override
    public void deleteById(UUID id) {
        if (!orderRepository.existsById(id)) {
            throw new IllegalArgumentException("Order not found");
        }

        orderRepository.deleteById(id);
    }
}
