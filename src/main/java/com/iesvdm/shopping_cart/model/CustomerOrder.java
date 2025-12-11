package com.iesvdm.shopping_cart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerOrder {

    private Long id;

    private String orderNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime createdAt;

    private String status;

    private BigDecimal grossTotal;

    private BigDecimal discountTotal;

    private BigDecimal finalTotal;

    private String paymentMethod;

    private String paymentStatus;

    private String paymentCountry;

    private String billingName;

    private String billingTaxId;

    private String billingStreet;

    private String billingCity;

    private String billingPostalCode;

    private String billingCountry;

    private String shippingName;

    private String shippingStreet;

    private String shippingCity;

    private String shippingPostalCode;

    private String shippingCountry;

    public CustomerOrder() {
    }

    public CustomerOrder(Long id, String orderNumber, LocalDateTime createdAt, String status, BigDecimal grossTotal, BigDecimal discountTotal, BigDecimal finalTotal, String paymentMethod, String paymentStatus, String paymentCountry, String billingName, String billingTaxId, String billingStreet, String billingCity, String billingPostalCode, String billingCountry, String shippingName, String shippingStreet, String shippingCity, String shippingPostalCode, String shippingCountry) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.createdAt = createdAt;
        this.status = status;
        this.grossTotal = grossTotal;
        this.discountTotal = discountTotal;
        this.finalTotal = finalTotal;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
        this.paymentCountry = paymentCountry;
        this.billingName = billingName;
        this.billingTaxId = billingTaxId;
        this.billingStreet = billingStreet;
        this.billingCity = billingCity;
        this.billingPostalCode = billingPostalCode;
        this.billingCountry = billingCountry;
        this.shippingName = shippingName;
        this.shippingStreet = shippingStreet;
        this.shippingCity = shippingCity;
        this.shippingPostalCode = shippingPostalCode;
        this.shippingCountry = shippingCountry;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getGrossTotal() {
        return grossTotal;
    }

    public void setGrossTotal(BigDecimal grossTotal) {
        this.grossTotal = grossTotal;
    }

    public BigDecimal getDiscountTotal() {
        return discountTotal;
    }

    public void setDiscountTotal(BigDecimal discountTotal) {
        this.discountTotal = discountTotal;
    }

    public BigDecimal getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(BigDecimal finalTotal) {
        this.finalTotal = finalTotal;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getPaymentCountry() {
        return paymentCountry;
    }

    public void setPaymentCountry(String paymentCountry) {
        this.paymentCountry = paymentCountry;
    }

    public String getBillingName() {
        return billingName;
    }

    public void setBillingName(String billingName) {
        this.billingName = billingName;
    }

    public String getBillingTaxId() {
        return billingTaxId;
    }

    public void setBillingTaxId(String billingTaxId) {
        this.billingTaxId = billingTaxId;
    }

    public String getBillingStreet() {
        return billingStreet;
    }

    public void setBillingStreet(String billingStreet) {
        this.billingStreet = billingStreet;
    }

    public String getBillingCity() {
        return billingCity;
    }

    public void setBillingCity(String billingCity) {
        this.billingCity = billingCity;
    }

    public String getBillingPostalCode() {
        return billingPostalCode;
    }

    public void setBillingPostalCode(String billingPostalCode) {
        this.billingPostalCode = billingPostalCode;
    }

    public String getBillingCountry() {
        return billingCountry;
    }

    public void setBillingCountry(String billingCountry) {
        this.billingCountry = billingCountry;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingStreet() {
        return shippingStreet;
    }

    public void setShippingStreet(String shippingStreet) {
        this.shippingStreet = shippingStreet;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingPostalCode() {
        return shippingPostalCode;
    }

    public void setShippingPostalCode(String shippingPostalCode) {
        this.shippingPostalCode = shippingPostalCode;
    }

    public String getShippingCountry() {
        return shippingCountry;
    }

    public void setShippingCountry(String shippingCountry) {
        this.shippingCountry = shippingCountry;
    }
}
