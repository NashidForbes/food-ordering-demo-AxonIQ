package io.axoniq.foodordering.coreapi.data.domain.interfaces;

import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductsRepository extends JpaRepository<ProductEntity, UUID> {

    ProductEntity findByProductId(UUID productId);
    ProductEntity findByProductIdOrName(UUID productId, String name);
}
