package com.siddhi.order_service.event;

import org.springframework.stereotype.Component;

@Component
public class LoggingOrderEventPublisher implements OrderEventPublisher{


     @Override
    public void publishOrderCreated(OrderCreatedEvent event) {
        System.out.println(
            "EVENT: OrderCreated | orderId=" + event.getOrderId()
            + " | product=" + event.getProductName()
            + " | quantity=" + event.getQuantity()
            + " | amount=" + event.getAmount()
            + " | at=" + event.getOccurredAt()
        );
    }

    @Override
    public void publishOrderStatusChanged(OrderStatusChangedEvent event) {
        System.out.println(
            "EVENT: OrderStatusChanged | orderId=" + event.getOrderId()
            + " | " + event.getOldStatus() + " -> " + event.getNewStatus()
            + " | at=" + event.getOccurredAt()
        );
    }
    
}
