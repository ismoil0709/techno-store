package uz.pdp.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uz.pdp.dto.UserRegistrationDto;
import uz.pdp.service.UserService;

@RequiredArgsConstructor
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("login")
    public ModelAndView login(@RequestParam(value = "error",required = false) String error){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/auth/login");
        if (error != null) modelAndView.addObject("error",error);
        return modelAndView;
    }

    @GetMapping("/register")
    public String register(){
        return "/auth/register";
    }

    @PostMapping("/register")
    public ModelAndView register(UserRegistrationDto user) {
        ModelAndView modelAndView = new ModelAndView();
        try{
            userService.register(user);
        }catch (Exception e){
            modelAndView.setViewName("/auth/register");
            modelAndView.addObject("error",e.getMessage());
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/login");
        return modelAndView;
    }
}
