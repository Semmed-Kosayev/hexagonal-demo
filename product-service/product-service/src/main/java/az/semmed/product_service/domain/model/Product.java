package az.semmed.product_service.domain.model;

import java.util.UUID;

public class Product {

    private final UUID id;
    private String name;
    private Money price;

    public Product(UUID id, String name, Money price) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public void rename(String newName) {
        if (newName == null || newName.isBlank()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        this.name = newName;
    }

    public void changePrice(Money newPrice) {
        this.price = newPrice;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{id=%s, name='%s', price=%s}"
                .formatted(id, name, price);
    }
}
