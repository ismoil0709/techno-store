package uz.pdp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Role;
import uz.pdp.entity.User;

import java.util.List;
import java.util.Set;

@Repository
public interface UserRepo extends CrudRepository<User,Long>{
    User getUserByUsername(String username);
    User getUserByPhoneNumber(String phoneNumber);
    User getUserByEmail(String email);
    @Query("SELECT u FROM User u JOIN u.roles r WHERE r IN :roles")
    List<User> getUsersByRoles(@Param("roles") Set<Role> roles);
}
