package io.axoniq.foodordering.coreapi.data.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;

@Data
@Table(name="FoodCart")
@Entity
public class FoodCartEntity implements Serializable {

    private static final long serialVersionUID = -165060882983026237L;

    @Id
    private UUID foodCartId;
    @ElementCollection
    @MapKeyColumn(name="productId")
    @Column(name="quantity")
    private Map<UUID, Integer> products;
}
