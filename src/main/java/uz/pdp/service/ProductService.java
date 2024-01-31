package uz.pdp.service;

import jakarta.servlet.http.Part;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.dto.ProductDto;
import uz.pdp.entity.Product;
import uz.pdp.entity.enums.ProductType;

import java.util.List;

@Service
public interface ProductService {
    void save(ProductDto product);
    void update(Product product);
    void delete(Long id);
    Product getById(Long id);
    List<Product> getAll();
    List<Product> getByTitle(String title);
    List<Product> getByProductType(ProductType productType);
    List<Product> getByPrice(Double price);
}
