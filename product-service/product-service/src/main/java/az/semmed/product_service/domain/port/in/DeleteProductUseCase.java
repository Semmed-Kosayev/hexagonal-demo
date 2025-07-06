package az.semmed.product_service.domain.port.in;

import java.util.UUID;

public interface DeleteProductUseCase {

    void deleteProduct(UUID productId);
}
