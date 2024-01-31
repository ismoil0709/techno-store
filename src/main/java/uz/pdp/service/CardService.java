package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.dto.CardDto;
import uz.pdp.entity.Card;
import uz.pdp.entity.enums.CardType;

import java.util.List;

@Service
public interface CardService {
    void addCard(CardDto card);
    void updateCard(Card card);
    void deleteCard(Long id);
    Card getById(Long id);
    List<Card> getAllCards();
    Card getByCardNumber(String cardNumber);
    List<Card> getByCardType(CardType cardType);
}
