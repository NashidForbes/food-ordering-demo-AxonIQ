package io.axoniq.foodordering.coreapi.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Entity
public class ProductEntity implements Serializable {
        private static final long serialVersionUID = -6170316513538380255L;
        @Id
        private UUID productId;
        //@Column(unique = true)
        private String title;
        private BigDecimal price;
        private Integer quantity;
}
