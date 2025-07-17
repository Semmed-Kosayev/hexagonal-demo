package az.semmed.notificationservice.domain.port.out;

import az.semmed.notificationservice.domain.model.Notification;

public interface NotificationRepository {

    void save(Notification notification);

    //TODO: add other methods

}
