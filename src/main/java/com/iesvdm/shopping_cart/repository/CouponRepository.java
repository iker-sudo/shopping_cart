package com.iesvdm.shopping_cart.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.iesvdm.shopping_cart.model.Coupon;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CouponRepository {

    private final JdbcTemplate jdbcTemplate;

    /**
     * RowMapper personalizado para mapear correctamente las columnas de la BD
     */
    private final RowMapper<Coupon> couponRowMapper = (rs, rowNum) -> {
        Coupon coupon = new Coupon();
        coupon.setId(rs.getInt("id"));
        coupon.setCode(rs.getString("code"));
        coupon.setDescription(rs.getString("description"));
        coupon.setDiscountType(Coupon.DiscountType.valueOf(rs.getString("discount_type")));
        coupon.setDiscountValue(rs.getBigDecimal("discount_value"));
        coupon.setActive(rs.getBoolean("active"));

        // Mapear fechas
        var validFrom = rs.getTimestamp("valid_from");
        if (validFrom != null) {
            coupon.setValidFrom(validFrom.toLocalDateTime());
        }

        var validTo = rs.getTimestamp("valid_to");
        if (validTo != null) {
            coupon.setValidTo(validTo.toLocalDateTime());
        }

        return coupon;
    };

    public List<Coupon> findAll() {
        return jdbcTemplate.query("SELECT * FROM coupon", couponRowMapper);
    }

    public Optional<Coupon> findByCode(String code) {
        var results = jdbcTemplate.query(
                "SELECT * FROM coupon WHERE code = ?",
                couponRowMapper,
                code
        );
        return results.stream().findFirst();
    }

    public Optional<Coupon> findValidCouponByCode(String code) {
        var results = jdbcTemplate.query(
                "SELECT * FROM coupon WHERE code = ? AND active = TRUE AND NOW() BETWEEN valid_from AND valid_to",
                couponRowMapper,
                code
        );
        return results.stream().findFirst();
    }

    public List<Coupon> findActiveCoupons() {
        return jdbcTemplate.query(
                "SELECT * FROM coupon WHERE active = TRUE AND NOW() BETWEEN valid_from AND valid_to",
                couponRowMapper
        );
    }
}

