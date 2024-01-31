package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.entity.Order;
import uz.pdp.entity.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface OrderService {
    void createOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(Long id);
    Order getById(Long id);
    List<Order> getAll();
    List<Order> getByCustomerId(Long id);
    List<Order> getByStartDate(LocalDateTime startDate);
    List<Order> getByEndDate(LocalDateTime endDate);
    List<Order> getByPaymentType(PaymentType paymentType);
}
