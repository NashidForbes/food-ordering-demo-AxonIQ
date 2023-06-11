package io.axoniq.foodordering.command.model;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;


@Data
public class CreateProductRestModel {
    //private UUID productId;
    //@Column(unique = true)
    @NotBlank(message = "Product name is a required field")
    private String name;
    @DecimalMin(value = "0.0", inclusive = false)
    @DecimalMax(value = "999.0", inclusive = false)
    @Digits(integer=3, fraction=2)
    private BigDecimal price;
    @Min(value = 1, message = "Quantity cannot be lower than 1")
    @Max(value = 10, message = "Quantity cannot be larger than 10")
    private Integer quantity;
}
