package com.example.playground.Domain.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductDomain extends GenericDomain {
    private String name;
    private String description;
    private BigDecimal price;
}
