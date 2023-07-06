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
    private String foodCartId;
    @JoinTable(name = "FoodCartEntityProducts")
    @ElementCollection
    private Map<String, Integer> products;
}
