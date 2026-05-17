package com.example.order.controller;

import com.example.order.domain.Order;
import com.example.order.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@Tag(name = "Order", description = "주문 조회 API — 강의 ch01 베이스라인")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "주문 목록", description = "메모리에 적재된 주문을 모두 반환합니다.")
    @ApiResponse(responseCode = "200", description = "성공")
    public List<Order> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "주문 단건", description = "주문 ID로 단건 조회.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "OK"),
        @ApiResponse(responseCode = "404", description = "주문 없음")
    })
    public ResponseEntity<Order> getOne(@PathVariable long id) {
        return service.findById(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
