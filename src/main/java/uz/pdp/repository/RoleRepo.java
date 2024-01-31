package uz.pdp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.pdp.entity.Permission;
import uz.pdp.entity.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepo extends CrudRepository<Role,Long> {
    @Query("SELECT r FROM Role r JOIN r.permissions p WHERE p IN :permissions")
    List<Role> getRolesByPermissions(@Param("permissions") Set<Permission> permissions);
    Role getRoleByName(String name);
    Role getRoleByCode(String code);
}
