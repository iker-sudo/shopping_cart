package com.iesvdm.shopping_cart.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    
    private Integer id;
    
    private String name;
    
    private String description;
    
    private BigDecimal price;
    
    private Boolean active;
}
