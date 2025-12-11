package com.iesvdm.shopping_cart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iesvdm.shopping_cart.model.CustomerOrder;
import com.iesvdm.shopping_cart.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<CustomerOrder> findAll() {
        return orderRepository.findAll();
    }

    public Optional<CustomerOrder> findById(Long id) {
        return orderRepository.findById(id);
    }

    @Transactional
    public CustomerOrder create(CustomerOrder order) {
        return orderRepository.insert(order);
    }

    @Transactional
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
