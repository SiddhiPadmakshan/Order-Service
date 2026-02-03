package com.siddhi.order_service.event;

public interface OrderEventPublisher {

    void publishOrderCreated(OrderCreatedEvent event);
    void publishOrderStatusChanged(OrderStatusChangedEvent event);
}
