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
public class OrderItem {
    
    private Long id;
    
    private Long orderId;
    
    private String productName;
    
    private BigDecimal unitPrice;
    
    private Integer quantity;
    
    private BigDecimal lineTotal;
    
    private Integer productId;
}
