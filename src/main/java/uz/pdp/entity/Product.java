package uz.pdp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import uz.pdp.dto.ProductDto;
import uz.pdp.entity.enums.ProductType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String imgPath;
    private Double price;
    private Boolean isAvailable = true;
    private ProductType productType;
    private Integer amount;

    public Product(ProductDto product) {
        this.title = product.title();
        this.description = product.description();
        this.price = product.price();
        this.productType = product.productType();
        this.amount = product.amount();
    }
}
