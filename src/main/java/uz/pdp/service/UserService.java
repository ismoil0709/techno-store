package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.dto.UserLoginDto;
import uz.pdp.dto.UserRegistrationDto;
import uz.pdp.entity.User;

import java.util.List;

@Service
public interface UserService {
    void register(UserRegistrationDto customer);
    User login(UserLoginDto loginDto);
    void editProfile(User user);
    void deleteProfile(Long id);
    User getById(Long id);
    List<User> getAll();
    User getByUsername(String username);
    User getByPhoneNumber(String phoneNumber);
    User getByEmail(String email);
    List<User> getByRoles(String... roles);
}
