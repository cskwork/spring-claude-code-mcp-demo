package com.example.order.service;

import com.example.order.domain.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class OrderService {

    private final AtomicLong sequence = new AtomicLong(0);
    private final List<Order> orders = new CopyOnWriteArrayList<>();

    public OrderService() {
        seed();
    }

    public List<Order> findAll() {
        return List.copyOf(orders);
    }

    public Optional<Order> findById(long id) {
        return orders.stream()
            .filter(o -> o.id() == id)
            .findFirst();
    }

    private void seed() {
        LocalDateTime now = LocalDateTime.now();
        orders.add(new Order(sequence.incrementAndGet(), "홍길동", "올리브영 토너", 2, 18_000, now.minusHours(3)));
        orders.add(new Order(sequence.incrementAndGet(), "임꺽정", "녹차 라테 1L", 1, 7_500, now.minusHours(1)));
        orders.add(new Order(sequence.incrementAndGet(), "장영실", "노트북 거치대", 1, 32_000, now.minusMinutes(10)));
    }
}
