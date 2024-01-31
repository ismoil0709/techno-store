package uz.pdp.dto;

import uz.pdp.entity.enums.CardType;

import java.time.LocalDate;

public record CardDto (String cardNumber, String cardName, CardType cardType, LocalDate expirationDate){
}
