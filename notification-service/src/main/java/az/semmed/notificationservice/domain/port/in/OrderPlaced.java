package az.semmed.notificationservice.domain.port.in;

import java.util.UUID;

public interface OrderPlaced {

    void orderPlaced(UUID orderId);

}
