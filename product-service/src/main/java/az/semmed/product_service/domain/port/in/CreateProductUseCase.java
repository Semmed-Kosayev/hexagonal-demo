package az.semmed.product_service.domain.port.in;

import az.semmed.product_service.domain.model.Money;
import az.semmed.product_service.domain.model.Product;

public interface CreateProductUseCase {

    Product createProduct(String name, Money price);
}
