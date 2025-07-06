package az.semmed.product_service.application.service;

import az.semmed.product_service.domain.model.Money;
import az.semmed.product_service.domain.model.Product;
import az.semmed.product_service.domain.port.in.CreateProductUseCase;
import az.semmed.product_service.domain.port.in.DeleteProductUseCase;
import az.semmed.product_service.domain.port.in.GetAllProductsUseCase;
import az.semmed.product_service.domain.port.in.RenameProductUseCase;
import az.semmed.product_service.domain.port.out.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ProductService implements
        CreateProductUseCase,
        RenameProductUseCase,
        GetAllProductsUseCase,
        DeleteProductUseCase
{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createProduct(String name, Money price) {
        Product product = new Product(UUID.randomUUID(), name, price);
        productRepository.save(product);
        return product;
    }

    @Override
    public void renameProduct(UUID productId, String newName) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.rename(newName);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.deleteById(productId);
    }
}
