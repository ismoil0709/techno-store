package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.entity.Basket;

import java.util.List;

@Service
public interface BasketService {
    void createBasket(Long customerId);
    void addProductToBasket(Long basketId,Long... products);
    void deleteProductToBasket(Long basketId,Long... products);
    void deleteBasket(Long basketId);
    Basket getBasketById(Long basketId);
    List<Basket> getAllBaskets();
    Basket getBasketByCustomerId(Long basketId,Long customerId);
}
