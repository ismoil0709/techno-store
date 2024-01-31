package uz.pdp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Order;
import uz.pdp.entity.enums.PaymentType;
import uz.pdp.repository.OrderRepo;
import uz.pdp.service.OrderService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepo orderRepo;

    @Override
    public void createOrder(Order order) {
        if (order.getUser() == null)
            throw new IllegalArgumentException("User cannot be null");
        if (order.getProducts() == null || order.getProducts().isEmpty())
            throw new IllegalArgumentException("Products cannot be null or empty");
        if (order.getAmount() == null)
            throw new IllegalArgumentException("Amount cannot be null");
        if (order.getPaymentType() == null)
            throw new IllegalArgumentException("Payment type cannot be null");
        order.setStartDate(LocalDateTime.now());
        orderRepo.save(order);
    }

    @Override
    public void updateOrder(Order order) {
        if (order == null)
            throw new IllegalArgumentException("Order cannot be null");
        if (order.getId() == null)
            throw new IllegalArgumentException("Order id cannot be null");
        Optional<Order> byId = orderRepo.findById(order.getId());
        if (byId.isEmpty())
            throw new IllegalArgumentException("Order not found");
        Order order1 = byId.get();
        orderRepo.save(
                Order.builder()
                        .id(order.getId())
                        .user(Objects.requireNonNullElse(order.getUser(), order1.getUser()))
                        .products(Objects.requireNonNullElse(order.getProducts(),order1.getProducts()))
                        .amount(Objects.requireNonNullElse(order.getAmount(),order1.getAmount()))
                        .paymentType(Objects.requireNonNullElse(order.getPaymentType(),order1.getPaymentType()))
                        .startDate(Objects.requireNonNullElse(order.getStartDate(),order1.getStartDate()))
                        .endDate(Objects.requireNonNullElse(order.getEndDate(),order1.getEndDate()))
                        .build()
        );
    }

    @Override
    public void deleteOrder(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Order id cannot be null");
        Optional<Order> byId = orderRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Order not found");
        orderRepo.delete(byId.get());
    }

    @Override
    public Order getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Order id cannot be null");
        Optional<Order> byId = orderRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Order not found");
        return byId.get();
    }

    @Override
    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        orderRepo.findAll().iterator().forEachRemaining(orders::add);
        if (orders.isEmpty())
            throw new IllegalArgumentException("No orders found");
        return orders;
    }

    @Override
    public List<Order> getByCustomerId(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Customer id cannot be null");
        List<Order> ordersByCustomerId = orderRepo.getOrdersByUserId(id);
        if (ordersByCustomerId == null || ordersByCustomerId.isEmpty())
            throw new IllegalArgumentException("No orders found");
        return ordersByCustomerId;
    }

    @Override
    public List<Order> getByStartDate(LocalDateTime startDate) {
        if (startDate == null)
            throw new IllegalArgumentException("Start date cannot be null");
        List<Order> ordersByStartDate = orderRepo.getOrdersByStartDate(startDate);
        if (ordersByStartDate == null || ordersByStartDate.isEmpty())
            throw new IllegalArgumentException("No orders found");
        return ordersByStartDate;
    }

    @Override
    public List<Order> getByEndDate(LocalDateTime endDate) {
        if (endDate == null)
            throw new IllegalArgumentException("End date cannot be null");
        List<Order> ordersByEndDate = orderRepo.getOrdersByEndDate(endDate);
        if (ordersByEndDate == null || ordersByEndDate.isEmpty())
            throw new IllegalArgumentException("No orders found");
        return ordersByEndDate;
    }

    @Override
    public List<Order> getByPaymentType(PaymentType paymentType) {
        if (paymentType == null)
            throw new IllegalArgumentException("Payment type cannot be null");
        List<Order> ordersByPaymentType = orderRepo.getOrdersByPaymentType(paymentType);
        if (ordersByPaymentType == null || ordersByPaymentType.isEmpty())
            throw new IllegalArgumentException("No orders found");
        return ordersByPaymentType;
    }
}
