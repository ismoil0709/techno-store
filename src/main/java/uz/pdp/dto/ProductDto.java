package uz.pdp.dto;

import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.enums.ProductType;

public record ProductDto(String title, String description, Double price, ProductType productType, Integer amount,MultipartFile file) {
}
