package az.semmed.order_service.domain.port.in;

import java.util.UUID;

public interface ShipOrderUseCase {

    void ship(UUID orderId);
}
