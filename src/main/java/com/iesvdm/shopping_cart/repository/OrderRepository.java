package com.iesvdm.shopping_cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iesvdm.shopping_cart.model.CustomerOrder;

@Repository
public class OrderRepository {

    private final JdbcTemplate jdbcTemplate;

    public OrderRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("customer_order")
                .usingGeneratedKeyColumns("id");
    }

    public List<CustomerOrder> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM customer_order",
                BeanPropertyRowMapper.newInstance(CustomerOrder.class)
        );
    }

    public Optional<CustomerOrder> findById(Long id) {
        var results = jdbcTemplate.query(
                "SELECT * FROM customer_order WHERE id = ?",
                BeanPropertyRowMapper.newInstance(CustomerOrder.class),
                id
        );
        return results.stream().findFirst();
    }

    public CustomerOrder insert(CustomerOrder order) {
        var params = new BeanPropertySqlParameterSource(order);
        Number key = simpleJdbcInsert().executeAndReturnKey(params);
        if (key != null) {
            order.setId(key.longValue());
        }
        return order;
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM customer_order WHERE id = ?", id);
    }
}
