package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dto.ProductDto;
import uz.pdp.entity.enums.ProductType;
import uz.pdp.service.ProductService;
import uz.pdp.service.UserService;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final ProductService productService;
    private final UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/upload")
    public String upload(){
        return "/admin/upload";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "upload")
    public String upload(@ModelAttribute ProductDto productDto){
        productService.save(productDto);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('SHOW USERS')")
    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users",userService.getAll());
        return "/admin/users";
    }

    @PreAuthorize("hasAuthority('SHOW PRODUCTS')")
    @GetMapping("/products")
    public String products(Model model){
        System.out.println(productService.getAll());
        model.addAttribute("allTypes", ProductType.values());
        model.addAttribute("products",productService.getAll());
        return "/admin/products";
    }


}
