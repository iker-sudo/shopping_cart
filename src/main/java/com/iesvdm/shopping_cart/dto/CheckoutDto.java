package com.iesvdm.shopping_cart.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}

