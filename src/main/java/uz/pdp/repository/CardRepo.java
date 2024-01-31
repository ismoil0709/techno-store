package uz.pdp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Card;
import uz.pdp.entity.enums.CardType;

import java.util.List;

@Repository
public interface CardRepo extends CrudRepository<Card,Long>{
    Card getCardByCardNumber(String cardNumber);
    List<Card> getCardsByCardType(CardType cardType);
}
