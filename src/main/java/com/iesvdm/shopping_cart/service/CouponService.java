package com.iesvdm.shopping_cart.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.iesvdm.shopping_cart.model.Coupon;
import com.iesvdm.shopping_cart.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    /**
     * Busca un cupón válido por su código.
     * Verifica que esté activo y dentro del rango de fechas válidas.
     */
    public Optional<Coupon> findValidCouponByCode(String code) {
        return couponRepository.findValidCouponByCode(code);
    }

    /**
     * Obtiene todos los cupones activos y válidos.
     */
    public List<Coupon> findActiveCoupons() {
        return couponRepository.findActiveCoupons();
    }

    /**
     * Calcula el descuento aplicable según el tipo de cupón.
     *
     * @param coupon Cupón a aplicar
     * @param grossTotal Total bruto antes de descuento
     * @return Cantidad de descuento a aplicar
     */
    public BigDecimal calculateDiscount(Coupon coupon, BigDecimal grossTotal) {
        if (coupon == null || grossTotal == null || grossTotal.compareTo(BigDecimal.ZERO) <= 0) {
            return BigDecimal.ZERO;
        }

        return switch (coupon.getDiscountType()) {
            case PERCENT -> {
                // Descuento porcentual: grossTotal * (discountValue / 100)
                BigDecimal percentage = coupon.getDiscountValue().divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
                yield grossTotal.multiply(percentage).setScale(2, RoundingMode.HALF_UP);
            }
            case FIXED_AMOUNT -> {
                // Descuento fijo: no puede ser mayor que el total
                BigDecimal discount = coupon.getDiscountValue();
                yield discount.compareTo(grossTotal) > 0 ? grossTotal : discount;
            }
        };
    }

    /**
     * Calcula el total final después de aplicar el descuento.
     */
    public BigDecimal calculateFinalTotal(BigDecimal grossTotal, BigDecimal discountTotal) {
        if (grossTotal == null) {
            return BigDecimal.ZERO;
        }
        if (discountTotal == null) {
            return grossTotal;
        }

        BigDecimal finalTotal = grossTotal.subtract(discountTotal);
        // Asegurarse de que el total no sea negativo
        return finalTotal.compareTo(BigDecimal.ZERO) < 0 ? BigDecimal.ZERO : finalTotal;
    }
}

