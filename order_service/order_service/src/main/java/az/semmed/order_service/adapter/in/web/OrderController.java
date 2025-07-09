package az.semmed.order_service.adapter.in.web;

import az.semmed.order_service.adapter.in.web.dto.PlaceOrderRequest;
import az.semmed.order_service.application.dto.OrderItemCommand;
import az.semmed.order_service.domain.port.in.CancelOrderUseCase;
import az.semmed.order_service.domain.port.in.PlaceOrderUseCase;
import az.semmed.order_service.domain.port.in.ShipOrderUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final PlaceOrderUseCase placeOrderUseCase;
    private final CancelOrderUseCase cancelOrderUseCase;
    private final ShipOrderUseCase shipOrderUseCase;

    public OrderController(PlaceOrderUseCase placeOrderUseCase, CancelOrderUseCase cancelOrderUseCase, ShipOrderUseCase shipOrderUseCase) {
        this.placeOrderUseCase = placeOrderUseCase;
        this.cancelOrderUseCase = cancelOrderUseCase;
        this.shipOrderUseCase = shipOrderUseCase;
    }

    @PostMapping
    public ResponseEntity<UUID> placeOrder(@RequestBody PlaceOrderRequest request) {
        List<OrderItemCommand> cmdItems = request.items().stream()
                .map(i -> new OrderItemCommand(i.productId(), i.quantity(), i.price()))
                .toList();

        UUID id = placeOrderUseCase.place(cmdItems);

        return ResponseEntity.ok(id);
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable UUID id) {
        cancelOrderUseCase.cancel(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/ship")
    public ResponseEntity<Void> ship(@PathVariable UUID id) {
        shipOrderUseCase.ship(id);
        return ResponseEntity.noContent().build();
    }
}
