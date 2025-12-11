package com.iesvdm.shopping_cart.dto;

public class CheckoutDto {

    // Código de cupón de descuento
    private String couponCode;

    // Datos de pago
    private String paymentMethod;
    private String paymentCountry;

    // Datos de facturación
    private String billingName;
    private String billingTaxId;
    private String billingStreet;
    private String billingCity;
    private String billingPostalCode;
    private String billingCountry;

    // Datos de envío
    private String shippingName;
    private String shippingStreet;
    private String shippingCity;
    private String shippingPostalCode;
    private String shippingCountry;

    // Flag para usar los mismos datos de facturación para envío
    private boolean sameAsbilling;

    public CheckoutDto() {
    }

    public CheckoutDto(String couponCode, String paymentMethod, String paymentCountry, String billingName, String billingTaxId, String billingStreet, String billingCity, String billingPostalCode, String billingCountry, String shippingName, String shippingStreet, String shippingCity, String shippingPostalCode, String shippingCountry, boolean sameAsbilling) {
        this.couponCode = couponCode;
        this.paymentMethod = paymentMethod;
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
        this.sameAsbilling = sameAsbilling;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
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

    public boolean isSameAsbilling() {
        return sameAsbilling;
    }

    public void setSameAsbilling(boolean sameAsbilling) {
        this.sameAsbilling = sameAsbilling;
    }
}

