package uz.pdp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.CardDto;
import uz.pdp.entity.Card;
import uz.pdp.entity.enums.CardType;
import uz.pdp.repository.CardRepo;
import uz.pdp.service.CardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {
    private final CardRepo cardRepo;

    @Override
    public void addCard(CardDto card) {
        if (card == null)
            throw new IllegalArgumentException("Card is null");
        if (card.cardName() == null || card.cardName().isEmpty() || card.cardName().isBlank())
            throw new IllegalArgumentException("Card name is null");
        if (card.cardNumber() == null || card.cardNumber().isEmpty() || card.cardNumber().isBlank())
            throw new IllegalArgumentException("Card number is null");
        if (card.cardType() == null) throw new IllegalArgumentException("Card type is null");
        if (card.expirationDate() == null) throw new IllegalArgumentException("Card expiration date is null");
        if (cardRepo.getCardByCardNumber(card.cardNumber()) != null)
            throw new IllegalArgumentException("Card already use");
        cardRepo.save(new Card(card));
    }

    @Override
    public void updateCard(Card card) {
        Optional<Card> byId = cardRepo.findById(card.getId());
        if (byId.isEmpty()) throw new IllegalArgumentException("Card not found");
        Card card1 = byId.get();
        cardRepo.save(
                Card.builder()
                        .id(card.getId())
                        .cardNumber(Objects.requireNonNullElse(card.getCardNumber(), card1.getCardNumber()))
                        .cardName(Objects.requireNonNullElse(card.getCardName(), card1.getCardName()))
                        .cardType(Objects.requireNonNullElse(card.getCardType(), card1.getCardType()))
                        .expirationDate(Objects.requireNonNullElse(card.getExpirationDate(), card1.getExpirationDate())).build());
    }

    @Override
    public void deleteCard(Long id) {
        Optional<Card> byId = cardRepo.findById(id);
        if (byId.isEmpty()) throw new IllegalArgumentException("Card not found");
        cardRepo.delete(byId.get());
    }

    @Override
    public Card getById(Long id) {
        Optional<Card> byId = cardRepo.findById(id);
        if (byId.isEmpty()) throw new IllegalArgumentException("Card not found");
        return byId.get();
    }

    @Override
    public List<Card> getAllCards() {
        List<Card> cards = new ArrayList<>();
        cardRepo.findAll().iterator().forEachRemaining(cards::add);
        if (cards.isEmpty()) throw new IllegalArgumentException("Cards is empty");
        return cards;
    }

    @Override
    public Card getByCardNumber(String cardNumber) {
        Card cardByCardNumber = cardRepo.getCardByCardNumber(cardNumber);
        if (cardByCardNumber == null) throw new IllegalArgumentException("Card not found");
        return cardByCardNumber;
    }

    @Override
    public List<Card> getByCardType(CardType cardType) {
        List<Card> cards = new ArrayList<>();
        cardRepo.getCardsByCardType(cardType).iterator().forEachRemaining(cards::add);
        if (cards.isEmpty()) throw new IllegalArgumentException("Cards is empty");
        return cards;
    }
}

