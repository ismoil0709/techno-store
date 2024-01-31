package uz.pdp.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.entity.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @ManyToMany
    private List<Product> products;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double amount;
    private PaymentType paymentType;
}
