package uz.pdp.service.impl;

import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.dto.ProductDto;
import uz.pdp.entity.Product;
import uz.pdp.entity.enums.ProductType;
import uz.pdp.repository.ProductRepo;
import uz.pdp.service.ProductService;
import uz.pdp.util.FileUtils;

import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;

    @Override
    public void save(ProductDto product) {
        if (product.title() == null || product.title().isBlank() || product.title().isEmpty())
            throw new IllegalArgumentException("Title cannot be null, empty, or blank");
        if (product.description() == null || product.description().isBlank() || product.description().isEmpty())
            throw new IllegalArgumentException("Description cannot be null, empty, or blank");
        if (product.price() == null)
            throw new IllegalArgumentException("Price cannot be null");
        if (product.productType() == null)
            throw new IllegalArgumentException("Product type cannot be null");
        if (product.amount() == null)
            throw new IllegalArgumentException("Amount cannot be null");
        if (product.file() == null)
            throw new IllegalArgumentException("Image cannot be null");
        Product product1 = new Product(product);
        try {
            String imgPath = FileUtils.path + product.title() + product.file().getOriginalFilename();
            Files.copy(product.file().getInputStream(), Path.of(imgPath), StandardCopyOption.REPLACE_EXISTING);
            product1.setImgPath(imgPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        productRepo.save(product1);
    }

    @Override
    public void update(Product product) {
        if (product == null)
            throw new IllegalArgumentException("Product cannot be null");
        if (product.getId() == null)
            throw new IllegalArgumentException("Product ID cannot be null");
        Optional<Product> byId = productRepo.findById(product.getId());
        if (byId.isEmpty())
            throw new IllegalArgumentException("Product not found");
        Product product1 = byId.get();
        System.out.println("Product 1 " + product1.getAmount());
        System.out.println("Product " + product.getAmount());
        productRepo.save(
                Product.builder()
                        .id(product.getId())
                        .title(Objects.requireNonNullElse(product.getTitle(),product1.getTitle()))
                        .description(Objects.requireNonNullElse(product.getDescription(),product1.getDescription()))
                        .imgPath(Objects.requireNonNullElse(product.getImgPath(),product1.getImgPath()))
                        .price(Objects.requireNonNullElse(product.getPrice(),product1.getPrice()))
                        .isAvailable(Objects.requireNonNullElse(product.getIsAvailable(),product1.getIsAvailable()))
                        .productType(Objects.requireNonNullElse(product.getProductType(),product1.getProductType()))
                        .amount(Objects.requireNonNullElse(product.getAmount(),product1.getAmount()))
                        .build()
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Product not found");
        productRepo.delete(byId.get());
    }

    @Override
    public Product getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("ID cannot be null");
        Optional<Product> byId = productRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Product not found");
        return byId.get();
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        productRepo.findAll().iterator().forEachRemaining(products::add);
        if (products.isEmpty())
            throw new RuntimeException("No products found");
        return products;
    }

    @Override
    public List<Product> getByTitle(String title) {
        if (title == null || title.isBlank() || title.isEmpty())
            throw new IllegalArgumentException("Title cannot be null or empty");
        List<Product> productsByTitle = productRepo.getProductsByTitle(title);
        if (productsByTitle.isEmpty())
            throw new RuntimeException("No products found");
        return productsByTitle;
    }

    @Override
    public List<Product> getByProductType(ProductType productType) {
        if (productType == null)
            throw new IllegalArgumentException("Product type cannot be null");
        List<Product> productsByProductType = productRepo.getProductsByProductType(productType);
        if (productsByProductType.isEmpty())
            throw new RuntimeException("No products found");
        return productsByProductType;
    }

    @Override
    public List<Product> getByPrice(Double price) {
        if (price == null)
            throw new IllegalArgumentException("Price cannot be null");
        List<Product> productsByPrice = productRepo.getProductsByPrice(price);
        if (productsByPrice.isEmpty())
            throw new RuntimeException("No products found");
        return productsByPrice;
    }
}
