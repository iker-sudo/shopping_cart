package com.iesvdm.shopping_cart.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Coupon {
    
    private Integer id;
    
    private String code;
    
    private String description;
    
    private DiscountType discountType;
    
    private BigDecimal discountValue;
    
    private Boolean active;
    
    private LocalDateTime validFrom;
    
    private LocalDateTime validTo;
    
    public enum DiscountType {
        PERCENT,
        FIXED_AMOUNT
    }
}
