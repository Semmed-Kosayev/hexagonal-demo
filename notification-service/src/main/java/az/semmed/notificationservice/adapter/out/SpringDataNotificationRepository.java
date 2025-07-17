package az.semmed.notificationservice.adapter.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataNotificationRepository extends JpaRepository<NotificationEntity, UUID> {
}
