package az.semmed.product_service.domain.port.in;

import az.semmed.product_service.domain.model.Product;

import java.util.List;

public interface GetAllProductsUseCase {

    List<Product> getAllProducts();
}
