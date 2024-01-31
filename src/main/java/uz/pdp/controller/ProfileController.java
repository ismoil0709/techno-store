package uz.pdp.controller;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.PathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.service.UserService;
import uz.pdp.util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {
    private final UserService userService;
    @GetMapping("/{id}")
    public String profile(@PathVariable("id") Long id, Model model){
        model.addAttribute("user",userService.getById(id));
        return "profile";
    }
    @PostMapping("/photo/{id}")
    public String upload(@RequestParam("file")MultipartFile file,@PathVariable("id") Long id) throws IOException {
        Files.copy(file.getInputStream(), Path.of(FileUtils.path  + "id=" + id + ".jpg"), StandardCopyOption.REPLACE_EXISTING);
        return "profile";
    }
    @GetMapping("/download/photo/{id}")
    public ResponseEntity<?> download(@PathVariable("id") Long id) throws IOException {
        PathResource pathResource = new PathResource(Path.of(FileUtils.path + "id="+id + ".jpg"));
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=file.jpg")
                .body(pathResource.getContentAsByteArray());
    }
}
