package com.example.order.domain;

import java.time.LocalDateTime;

public record Order(
    Long id,
    String customer,
    String product,
    int quantity,
    int totalKrw,
    LocalDateTime createdAt
) {}
