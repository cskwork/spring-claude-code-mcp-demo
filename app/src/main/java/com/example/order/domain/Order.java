package com.example.order.domain;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "주문 한 건")
public record Order(
    @Schema(description = "주문 ID", example = "1") Long id,
    @Schema(description = "고객명", example = "홍길동") String customer,
    @Schema(description = "상품명", example = "올리브영 토너") String product,
    @Schema(description = "수량", example = "2") int quantity,
    @Schema(description = "총액(KRW)", example = "18000") int totalKrw,
    @Schema(description = "주문 시각") LocalDateTime createdAt
) {}
