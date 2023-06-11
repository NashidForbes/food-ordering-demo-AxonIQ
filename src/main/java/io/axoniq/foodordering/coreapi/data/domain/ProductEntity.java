package io.axoniq.foodordering.coreapi.data.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Table(name="products")
@Entity
public class ProductEntity implements Serializable {
        private static final long serialVersionUID = -6170316513538380255L;
        @Id
        private UUID productId;
        //@Column(unique = true)
        private String name;
        private BigDecimal price;
        private Integer quantity;
}
