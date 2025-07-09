package az.semmed.order_service.domain.port.in;

import az.semmed.order_service.application.dto.OrderItemCommand;

import java.util.List;
import java.util.UUID;

public interface PlaceOrderUseCase {

    UUID place(List<OrderItemCommand> items);
}
