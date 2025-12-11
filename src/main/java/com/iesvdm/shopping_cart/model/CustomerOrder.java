package com.iesvdm.shopping_cart.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
}
