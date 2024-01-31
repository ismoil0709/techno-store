package uz.pdp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Product;
import uz.pdp.entity.enums.ProductType;

import java.util.List;

@Repository
public interface ProductRepo extends CrudRepository<Product,Long> {
    List<Product> getProductsByProductType(ProductType productType);
    List<Product> getProductsByTitle(String title);
    List<Product> getProductsByPrice(Double price);
}
