package com.iesvdm.shopping_cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Repository;

import com.iesvdm.shopping_cart.model.Product;

@Repository
public class ProductRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("product")
                .usingGeneratedKeyColumns("id");
    }

    public List<Product> findAll() {
        return jdbcTemplate.query(
            "SELECT * FROM product WHERE active = true",
            BeanPropertyRowMapper.newInstance(Product.class)
        );
    }

    public Optional<Product> findById(Integer id) {
        var results = jdbcTemplate.query(
            "SELECT * FROM product WHERE id = ?",
            BeanPropertyRowMapper.newInstance(Product.class),
            id
        );
        return results.stream().findFirst();
    }

    public Product insert(Product product) {
        var params = new BeanPropertySqlParameterSource(product);
        Number key = simpleJdbcInsert().executeAndReturnKey(params);
        if (key != null) {
            product.setId(key.intValue());
        }
        return product;
    }

    public int update(Product product) {
        return jdbcTemplate.update(
            "UPDATE product SET name = ?, description = ?, price = ?, active = ? WHERE id = ?",
            product.getName(), product.getDescription(), product.getPrice(), product.getActive(), product.getId()
        );
    }

    public int deleteById(Integer id) {
        return jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
    }
}

