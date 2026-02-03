package com.siddhi.order_service.event;

import java.time.Instant;

public class OrderCreatedEvent {

    private final Long orderId;
    private final String productName;
    private final int quantity;
    private final double amount;
    private final Instant occurredAt;

    public OrderCreatedEvent(Long orderId, String productName, int quantity, double amount) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.amount = amount;
        this.occurredAt = Instant.now();
    }

    public Long getOrderId() { return orderId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getAmount() { return amount; }
    public Instant getOccurredAt() { return occurredAt; }
}
