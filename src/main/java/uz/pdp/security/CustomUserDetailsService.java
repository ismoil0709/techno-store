package uz.pdp.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.entity.Role;
import uz.pdp.entity.User;
import uz.pdp.security.context.UserContext;
import uz.pdp.service.PermissionService;
import uz.pdp.service.RoleService;
import uz.pdp.service.UserService;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = userService.getByUsername(username);
        if (byUsername == null)
            throw new IllegalArgumentException("User not found");
        return new UserContext(byUsername);
    }
}
