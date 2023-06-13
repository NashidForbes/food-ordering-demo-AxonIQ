package io.axoniq.foodordering.coreapi.data.domain.interfaces;

import io.axoniq.foodordering.coreapi.data.domain.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findByProductIdOrName(UUID productId, String name);
}
