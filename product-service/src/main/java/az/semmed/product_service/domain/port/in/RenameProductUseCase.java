package az.semmed.product_service.domain.port.in;

import java.util.UUID;

public interface RenameProductUseCase {

    void renameProduct(UUID productId, String newName);
}
