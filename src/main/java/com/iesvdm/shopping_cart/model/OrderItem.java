package com.iesvdm.shopping_cart.model;

import java.math.BigDecimal;

public class OrderItem {
    
    private Long id;
    
    private Long orderId;
    
    private String productName;
    
    private BigDecimal unitPrice;
    
    private Integer quantity;
    
    private BigDecimal lineTotal;
    
    private Integer productId;

    public OrderItem() {
    }

    public OrderItem(Long id, Long orderId, String productName, BigDecimal unitPrice, Integer quantity, BigDecimal lineTotal, Integer productId) {
        this.id = id;
        this.orderId = orderId;
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.lineTotal = lineTotal;
        this.productId = productId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(BigDecimal lineTotal) {
        this.lineTotal = lineTotal;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
