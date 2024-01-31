package uz.pdp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Basket;

@Repository
public interface BasketRepo extends CrudRepository<Basket,Long> {
    Basket getBasketByUserId(Long customerId);
}
