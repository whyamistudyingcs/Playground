package com.example.playground.Presentation.DTO;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductCreationDTO extends GenericCreationDTO {
    private String name;
    private String description;
    private Double price;
}
