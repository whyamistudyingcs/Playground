package com.example.playground.Infrastructure.DBMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Table(name = "PRODUCT")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductEntity extends GenericEntity {
    @NotNull(message = "name is required")
    @Size(min=4,max=255)
    private String name;
    @NotNull(message = "description is required")
    @Size(min=4,max=255)
    private String description;
    @NotNull(message = "price is required")
    private BigDecimal price;
}
