package uz.pdp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import uz.pdp.dto.CardDto;
import uz.pdp.entity.enums.CardType;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cardNumber;
    private String cardName;
    private CardType cardType;
    private LocalDate expirationDate;

    public Card(CardDto card) {
        this.cardName = card.cardName();
        this.cardNumber = card.cardNumber();
        this.cardType = card.cardType();
        this.expirationDate = card.expirationDate();
    }
}
