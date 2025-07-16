package az.semmed.product_service.adapter.out;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    private UUID id;

    private String name;

    private BigDecimal priceAmount;
    private String priceCurrency;

    protected ProductEntity() {
        // JPA requires default constructor
    }

    public ProductEntity(UUID id, String name, BigDecimal priceAmount, String priceCurrency) {
        this.id = id;
        this.name = name;
        this.priceAmount = priceAmount;
        this.priceCurrency = priceCurrency;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPriceAmount() {
        return priceAmount;
    }

    public String getPriceCurrency() {
        return priceCurrency;
    }
}
