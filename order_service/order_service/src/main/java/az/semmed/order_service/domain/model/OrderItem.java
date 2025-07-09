package az.semmed.order_service.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {

    private final UUID productId;
    private final int quantity;
    private final BigDecimal price;

    public OrderItem(UUID productId, int quantity, BigDecimal price) {
        if (productId == null || price == null) {
            throw new IllegalArgumentException("productId and quantity cannot be null");
        }
        if (price.compareTo(BigDecimal.ZERO) < 0 || quantity < 0) {
            throw new IllegalArgumentException("price or quantity cannot be negative");
        }
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public BigDecimal getSubTotal() {
        return price.multiply(new BigDecimal(quantity));
    }

    public UUID getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
