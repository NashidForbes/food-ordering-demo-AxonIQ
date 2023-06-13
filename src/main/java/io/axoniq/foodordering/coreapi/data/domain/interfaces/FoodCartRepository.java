package io.axoniq.foodordering.coreapi.data.domain.interfaces;

import io.axoniq.foodordering.coreapi.data.domain.FoodCartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FoodCartRepository extends JpaRepository<FoodCartEntity, UUID> {
    FoodCartEntity findByFoodCartId(UUID productId);
}
