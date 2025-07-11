package az.semmed.product_service.adapter.in.web;

import az.semmed.product_service.adapter.in.web.dto.CreateProductRequest;
import az.semmed.product_service.adapter.in.web.dto.ProductResponse;
import az.semmed.product_service.adapter.in.web.dto.RenameProductRequest;
import az.semmed.product_service.domain.model.Money;
import az.semmed.product_service.domain.model.Product;
import az.semmed.product_service.domain.port.in.CreateProductUseCase;
import az.semmed.product_service.domain.port.in.DeleteProductUseCase;
import az.semmed.product_service.domain.port.in.GetAllProductsUseCase;
import az.semmed.product_service.domain.port.in.RenameProductUseCase;
import az.semmed.product_service.support.mapper.ProductMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final CreateProductUseCase createUseCase;
    private final RenameProductUseCase renameUseCase;
    private final GetAllProductsUseCase getAllProductsUseCase;
    private final DeleteProductUseCase deleteProductUseCase;
    private final ProductMapper productMapper;

    public ProductController(CreateProductUseCase createUseCase, RenameProductUseCase renameUseCase, GetAllProductsUseCase getAllProductsUseCase, DeleteProductUseCase deleteProductUseCase, ProductMapper productMapper) {
        this.createUseCase = createUseCase;
        this.renameUseCase = renameUseCase;
        this.getAllProductsUseCase = getAllProductsUseCase;
        this.deleteProductUseCase = deleteProductUseCase;
        this.productMapper = productMapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(
                getAllProductsUseCase.getAllProducts().stream()
                .map(productMapper::toProductResponse)
                .toList()
        );
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody CreateProductRequest request) {
        Product created = createUseCase.createProduct(request.name(), new Money(request.amount(), request.currency()));
        return ResponseEntity.ok(productMapper.toProductResponse(created));
    }

    @PutMapping("/{id}/rename")
    public ResponseEntity<Void> renameProduct(@PathVariable UUID id, @RequestBody RenameProductRequest request) {
        renameUseCase.renameProduct(id, request.newName());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        deleteProductUseCase.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
