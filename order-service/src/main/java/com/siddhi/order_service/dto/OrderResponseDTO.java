package com.siddhi.order_service.dto;

import com.siddhi.order_service.model.OrderStatus;

import lombok.Data;

@Data
public class OrderResponseDTO {
    private Long id;
    private String productName;
    private int quantity;
    private OrderStatus status;
}
