package io.axoniq.foodordering.coreapi.data.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="productlookup")
public class ProductLookupEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2788007460547645663L;

    @Id
    private UUID productId;

    @Column(unique=true)
    private String name;

}
