package uz.pdp.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.entity.Basket;
import uz.pdp.entity.Product;
import uz.pdp.entity.User;
import uz.pdp.repository.BasketRepo;
import uz.pdp.service.BasketService;
import uz.pdp.service.ProductService;
import uz.pdp.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BasketServiceImpl implements BasketService {
    private final BasketRepo basketRepo;
    private final UserService userService;
    private final ProductService productService;

    @Override
    public void createBasket(Long customerId) {
        User byId = userService.getById(customerId);
        if (byId == null) throw new IllegalArgumentException("User not found");
        basketRepo.save(Basket.builder().user(byId).build());
    }

    @Override
    public void addProductToBasket(Long basketId, Long... products) {
        Optional<Basket> byId = basketRepo.findById(basketId);
        if (byId.isEmpty()) throw new IllegalArgumentException("Basket not found");
        Basket basket = byId.get();
        if (basket.getProducts() == null) basket.setProducts(new ArrayList<>());
        Arrays.stream(products).toList().forEach(p -> {
            Product product = productService.getById(p);
            if (product == null) throw new IllegalArgumentException("Product not found");
            basket.getProducts().add(product);
        });
        basketRepo.save(basket);
    }

    @Override
    public void deleteProductToBasket(Long basketId, Long... products) {
        Optional<Basket> byId = basketRepo.findById(basketId);
        if (byId.isEmpty()) throw new IllegalArgumentException("Basket not found");
        Basket basket = byId.get();
        if (basket.getProducts() == null || basket.getProducts().isEmpty())
            throw new IllegalArgumentException("Basket is empty");
        Arrays.stream(products).toList().forEach(p -> {
            Product product = productService.getById(p);
            if (product == null) throw new IllegalArgumentException("Product not found");
            basket.getProducts().remove(product);
        });
        basketRepo.save(basket);
    }

    @Override
    public void deleteBasket(Long basketId) {
        Optional<Basket> byId = basketRepo.findById(basketId);
        if (byId.isEmpty()) throw new IllegalArgumentException("Basket not found");
        basketRepo.delete(byId.get());
    }

    @Override
    public Basket getBasketById(Long basketId) {
        Optional<Basket> byId = basketRepo.findById(basketId);
        if (byId.isEmpty()) throw new IllegalArgumentException("Basket not found");
        return byId.get();
    }

    @Override
    public List<Basket> getAllBaskets() {
        List<Basket> baskets = new ArrayList<>();
        basketRepo.findAll().iterator().forEachRemaining(baskets::add);
        if (baskets.isEmpty()) throw new IllegalArgumentException("Baskets is empty");
        return baskets;
    }

    @Override
    public Basket getBasketByCustomerId(Long basketId, Long customerId) {
        User byId = userService.getById(customerId);
        Optional<Basket> basket = basketRepo.findById(basketId);
        if (byId == null) throw new IllegalArgumentException("User not found");
        if (basket.isEmpty()) throw new IllegalArgumentException("Basket is empty");
        return basketRepo.getBasketByUserId(customerId);
    }
}
