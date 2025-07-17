package az.semmed.notificationservice.application.service;

import az.semmed.notificationservice.domain.model.Notification;
import az.semmed.notificationservice.domain.port.in.OrderPlaced;
import az.semmed.notificationservice.domain.port.out.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService implements OrderPlaced {

    private final NotificationRepository repository;

    @Override
    public void orderPlaced(UUID orderId) {
        System.out.println("Order placed with id: " + orderId);
        repository.save(
                new Notification(orderId, "Order with id " + orderId + "has been placed")
        );
    }
}
