package io.axoniq.foodordering.query.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRestModel {
    private UUID productId;
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
