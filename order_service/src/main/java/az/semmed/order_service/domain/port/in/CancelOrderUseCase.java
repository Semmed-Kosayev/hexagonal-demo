package az.semmed.order_service.domain.port.in;

import java.util.UUID;

public interface CancelOrderUseCase {

    void cancel(UUID orderId);
}
