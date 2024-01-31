package uz.pdp.repository;

import org.springframework.data.repository.CrudRepository;
import uz.pdp.entity.Permission;

public interface PermissionRepo extends CrudRepository<Permission,Long> {
    Permission getPermissionByName(String name);
    Permission getPermissionByCode(String code);
}
