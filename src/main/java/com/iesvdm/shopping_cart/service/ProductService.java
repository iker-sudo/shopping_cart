package com.iesvdm.shopping_cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesvdm.shopping_cart.model.Product;
import com.iesvdm.shopping_cart.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Integer id) {
        return productRepository.findById(id);
    }

    @Transactional
    public Product create(Product product) {
        return productRepository.insert(product);
    }

    @Transactional
    public void update(Product product) {
        productRepository.update(product);
    }

    @Transactional
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}

