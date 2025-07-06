package az.semmed.product_service.adapter.out;

import az.semmed.product_service.domain.model.Product;
import az.semmed.product_service.domain.port.out.ProductRepository;
import az.semmed.product_service.support.mapper.ProductMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ProductPersistenceAdapter implements ProductRepository {

    private final SpringDataProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductPersistenceAdapter(SpringDataProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public void save(Product product) {
        productRepository.save(
                productMapper.mapToProductEntity(product)
        );
    }

    @Override
    public Optional<Product> findById(UUID id) {
        return productRepository.findById(id).map(productMapper::mapToProduct);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream()
                .map(productMapper::mapToProduct)
                .toList();
    }
}
