package com.iesvdm.shopping_cart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    public Coupon() {
    }

    public Coupon(Integer id, String code, String description, DiscountType discountType, BigDecimal discountValue, Boolean active, LocalDateTime validFrom, LocalDateTime validTo) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.discountType = discountType;
        this.discountValue = discountValue;
        this.active = active;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public BigDecimal getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(BigDecimal discountValue) {
        this.discountValue = discountValue;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public LocalDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(LocalDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }
}
