package az.semmed.notificationservice.adapter.out;

import az.semmed.notificationservice.domain.model.Notification;
import az.semmed.notificationservice.domain.port.out.NotificationRepository;
import az.semmed.notificationservice.support.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements NotificationRepository {

    private final SpringDataNotificationRepository repository;
    private final NotificationMapper mapper;

    @Override
    public void save(Notification notification) {
        repository.save(
                mapper.toNotificationEntity(notification)
        );
    }
}
