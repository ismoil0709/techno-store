package uz.pdp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.UserLoginDto;
import uz.pdp.dto.UserRegistrationDto;
import uz.pdp.entity.Role;
import uz.pdp.entity.User;
import uz.pdp.repository.RoleRepo;
import uz.pdp.repository.UserRepo;
import uz.pdp.service.UserService;

import java.util.*;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    @Override
    public void register(UserRegistrationDto user) {
        if (user == null) throw new IllegalArgumentException("User is null");
        if (user.name() == null || user.name().isEmpty() || user.name().isBlank())
            throw new IllegalArgumentException("Name is null");
        if (user.username() == null || user.username().isEmpty() || user.username().isBlank())
            throw new IllegalArgumentException("Username is null");
        if (user.password() == null || user.password().isEmpty() || user.password().isBlank())
            throw new IllegalArgumentException("Password is null");
        if (user.phoneNumber() == null || user.phoneNumber().isEmpty() || user.phoneNumber().isBlank())
            throw new IllegalArgumentException("Phone number is null");

        if (userRepo.getUserByUsername(user.username()) != null)
            throw new IllegalArgumentException("Username already use");
        if (user.email() != null && userRepo.getUserByEmail(user.email()) != null)
            throw new IllegalArgumentException("Email already use");
        if (userRepo.getUserByPhoneNumber(user.phoneNumber()) != null)
            throw new IllegalArgumentException("Phone number already use");
        userRepo.save(new User(user));
    }

    @Override
    public User login(UserLoginDto loginDto) {
        if (loginDto.username() == null || loginDto.username().isEmpty() || loginDto.username().isBlank())
            throw new IllegalArgumentException("Username is null");
        if (loginDto.password() == null || loginDto.password().isEmpty() || loginDto.password().isBlank())
            throw new IllegalArgumentException("Password is null");
        User byUsername = userRepo.getUserByUsername(loginDto.username());
        if (byUsername == null)
            throw new IllegalArgumentException("User not found");
        if (!byUsername.getPassword().equals(loginDto.password()))
            throw new IllegalArgumentException("Incorrect password");
        return byUsername;
    }

    @Override
    public void editProfile(User user) {
        if (user == null)
            throw new IllegalArgumentException("User is null");
        if (user.getId() == null)
            throw new IllegalArgumentException("Id is null");
        Optional<User> byId = userRepo.findById(user.getId());
        if (byId.isEmpty())
            throw new IllegalArgumentException("User not found");
        User user1 = byId.get();
        userRepo.save(User.builder()
                .id(user.getId())
                .name(Objects.requireNonNullElse(user.getName(), user1.getName()))
                .username(Objects.requireNonNullElse(user.getUsername(), user1.getUsername()))
                .password(Objects.requireNonNullElse(user.getPassword(), user1.getPassword()))
                .email(Objects.requireNonNullElse(user.getEmail(), user1.getEmail()))
                .phoneNumber(Objects.requireNonNullElse(user.getPhoneNumber(), user1.getPhoneNumber()))
                .build());
    }

    @Override
    public void deleteProfile(Long id) {
        if (id == null) throw new IllegalArgumentException("Id is null");
        Optional<User> byId = userRepo.findById(id);
        if (byId.isEmpty()) throw new IllegalArgumentException("User not found");
        userRepo.delete(byId.get());
    }

    @Override
    public User getById(Long id) {
        if (id == null) throw new IllegalArgumentException("Id is null");
        Optional<User> byId = userRepo.findById(id);
        if (byId.isEmpty()) throw new IllegalArgumentException("User not found");
        return byId.get();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().iterator().forEachRemaining(users::add);
        if (users.isEmpty()) throw new IllegalArgumentException("Customers is empty");
        return users;
    }

    @Override
    public User getByUsername(String username) {
        if (username == null || username.isEmpty() || username.isBlank()) throw new IllegalArgumentException("Username is null");
        User byUsername = userRepo.getUserByUsername(username);
        if (byUsername == null) throw new IllegalArgumentException("User not found");
        return byUsername;
    }

    @Override
    public User getByPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.isBlank()) throw new IllegalArgumentException("Phone number is null");
        User byPhoneNumber = userRepo.getUserByPhoneNumber(phoneNumber);
        if (byPhoneNumber == null) throw new IllegalArgumentException("User not found");
        return byPhoneNumber;
    }

    @Override
    public User getByEmail(String email) {
        if (email == null || email.isEmpty() || email.isBlank()) throw new IllegalArgumentException("Email is null");
        User byEmail = userRepo.getUserByEmail(email);
        if (byEmail == null) throw new IllegalArgumentException("User not found");
        return byEmail;
    }

    @Override
    public List<User> getByRoles(String... roles) {
        if (roles == null || roles.length == 0) throw new IllegalArgumentException("Roles are null or empty");
        Set<Role> roleSet = new HashSet<>();
        for (String role : roles) {
            Role byRoleName = roleRepo.getRoleByName(role);
            if (byRoleName == null) throw new IllegalArgumentException("Role not found: " + role);
            roleSet.add(byRoleName);
        }
        return userRepo.getUsersByRoles(new HashSet<>(roleSet));
    }
}
