package uz.pdp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Order;
import uz.pdp.entity.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepo extends CrudRepository<Order,Long>{
    List<Order> getOrdersByUserId(Long id);
    List<Order> getOrdersByStartDate(LocalDateTime startDate);
    List<Order> getOrdersByEndDate(LocalDateTime endDate);
    List<Order> getOrdersByPaymentType(PaymentType paymentType);
}
