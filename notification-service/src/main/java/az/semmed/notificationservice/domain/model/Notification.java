package az.semmed.notificationservice.domain.model;

import java.util.UUID;

public class Notification {

    private UUID orderId;
    private String message;

    public Notification(UUID orderId, String message) {
        this.orderId = orderId;
        this.message = message;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
