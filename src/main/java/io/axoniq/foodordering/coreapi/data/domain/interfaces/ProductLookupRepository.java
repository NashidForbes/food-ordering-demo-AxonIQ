package io.axoniq.foodordering.coreapi.data.domain.interfaces;

import io.axoniq.foodordering.coreapi.data.domain.ProductLookupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductLookupRepository extends JpaRepository<ProductLookupEntity, String> {
    ProductLookupEntity findByProductIdOrTitle(String productId, String title);
}
