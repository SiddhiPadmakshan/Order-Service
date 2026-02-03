package com.siddhi.order_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.siddhi.order_service.dto.CreateOrderRequestDTO;
import com.siddhi.order_service.dto.OrderResponseDTO;
import com.siddhi.order_service.exception.IllegalStateChangeException;
import com.siddhi.order_service.exception.OrderNotFoundException;
import com.siddhi.order_service.model.Order;
import com.siddhi.order_service.model.OrderStatus;
import com.siddhi.order_service.repository.OrderRepository;
import com.siddhi.order_service.event.OrderCreatedEvent;
import com.siddhi.order_service.event.OrderEventPublisher;
import com.siddhi.order_service.event.OrderStatusChangedEvent;

@Service
public class OrderService {
    
    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;
    

    public OrderService(OrderRepository repository, OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
    }

    public OrderResponseDTO createOrder(CreateOrderRequestDTO request) {

        Order order = new Order();
            order.setProductName(request.getProductName());
            order.setQuantity(request.getQuantity());
            order.setStatus(OrderStatus.PENDING);
            order.setAmount( (double) 1 * request.getQuantity());

            Order savedOrder = repository.save(order);

            eventPublisher.publishOrderCreated(
        new OrderCreatedEvent(
                savedOrder.getId(),
                savedOrder.getProductName(),
                savedOrder.getQuantity(),
                savedOrder.getAmount()
        )
);

        return mapToResponseDTO(savedOrder);
    }



    public Order getOrder(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));
    }



    public Order updateStatus(Long orderId, OrderStatus newStatus) {
    Order order = repository.findById(orderId)
            .orElseThrow(() -> new OrderNotFoundException(orderId));

            OrderStatus oldStatus = order.getStatus();

        if (!isValidTransition(order.getStatus(), newStatus)) {
            throw new IllegalStateChangeException(
                "Invalid order status transition from " 
                + oldStatus + " to " + newStatus
            );
        }

            order.setStatus(newStatus);
            Order saved = repository.save(order);

            eventPublisher.publishOrderStatusChanged(
            new OrderStatusChangedEvent(orderId, oldStatus, newStatus)
            );


            return saved;
    }
    


    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }



    public Page<OrderResponseDTO> getAllOrders(Pageable pageable) {
        return repository.findAll(pageable)
            .map(this::mapToResponseDTO);
            
    }

    public OrderResponseDTO  getOrderById(Long id) {
         Order order = repository.findById(id)
            .orElseThrow(() -> new OrderNotFoundException(id));

        return mapToResponseDTO(order);
    }

    private OrderResponseDTO mapToResponseDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
            dto.setId(order.getId());
            dto.setProductName(order.getProductName());
            dto.setQuantity(order.getQuantity());
            dto.setStatus(order.getStatus());
            return dto;
    }

    private boolean isValidTransition(OrderStatus current, OrderStatus next) {
    return switch (current) {
        case PENDING -> next == OrderStatus.CONFIRMED || next == OrderStatus.CANCELLED;
        case CONFIRMED -> next == OrderStatus.DISPATCHED || next == OrderStatus.CANCELLED;
        case DISPATCHED -> next == OrderStatus.DELIVERED;
        case DELIVERED, CANCELLED -> false;
        };
    }

    
}