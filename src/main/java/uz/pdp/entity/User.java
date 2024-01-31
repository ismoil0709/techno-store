package uz.pdp.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import uz.pdp.dto.UserRegistrationDto;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String phoneNumber;
    private String email = "unknown";
    @ManyToMany
    private List<Product> likedProduct;
    @OneToMany
    private List<Card> cards;
    @OneToMany
    private List<Order> orders;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    public User(UserRegistrationDto customer) {
        this.name = customer.name();
        this.username = customer.username();
        this.email = customer.email();
        this.password = new BCryptPasswordEncoder().encode(customer.password());
        this.phoneNumber = customer.phoneNumber();
    }
}
