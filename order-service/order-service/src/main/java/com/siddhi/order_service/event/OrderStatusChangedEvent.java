package com.siddhi.order_service.event;

import com.siddhi.order_service.model.OrderStatus;
import java.time.Instant;

public class OrderStatusChangedEvent {

    private final Long orderId;
    private final OrderStatus oldStatus;
    private final OrderStatus newStatus;
    private final Instant occurredAt;

    public OrderStatusChangedEvent(Long orderId, OrderStatus oldStatus, OrderStatus newStatus) {
        this.orderId = orderId;
        this.oldStatus = oldStatus;
        this.newStatus = newStatus;
        this.occurredAt = Instant.now();
    }

    public Long getOrderId() { return orderId; }
    public OrderStatus getOldStatus() { return oldStatus; }
    public OrderStatus getNewStatus() { return newStatus; }
    public Instant getOccurredAt() { return occurredAt; }
}