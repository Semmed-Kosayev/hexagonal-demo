package az.semmed.product_service.support.mapper;

import az.semmed.product_service.adapter.in.web.dto.ProductResponse;
import az.semmed.product_service.adapter.out.ProductEntity;
import az.semmed.product_service.domain.model.Money;
import az.semmed.product_service.domain.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity mapToProductEntity(Product product) {
        return new ProductEntity(
                product.getId(),
                product.getName(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency()
        );
    }

    public Product mapToProduct(ProductEntity productEntity) {
        return new Product(
                productEntity.getId(),
                productEntity.getName(),
                new Money(productEntity.getPriceAmount(), productEntity.getPriceCurrency())
        );
    }

    public ProductResponse toProductResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getPrice().getAmount(),
                product.getPrice().getCurrency()
        );
    }
}
