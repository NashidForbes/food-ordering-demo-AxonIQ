package io.axoniq.foodordering.coreapi.data.domain.interfaces;

import io.axoniq.foodordering.coreapi.data.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<ProductEntity, String> {

    ProductEntity findByProductId(String productId);
    ProductEntity findByProductIdOrTitle(String productId, String title);
}
