package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.entity.Product;
import uz.pdp.service.ProductService;

@Controller
@RequestMapping("/admin/update")
@RequiredArgsConstructor
public class UpdateController {

    private final ProductService productService;

    @PatchMapping("/product")
    public ResponseEntity<?> product(@ModelAttribute Product product){
        productService.update(product);
        return ResponseEntity.ok().build();
    }
}
