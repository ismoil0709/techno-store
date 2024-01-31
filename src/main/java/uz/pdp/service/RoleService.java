package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.dto.RoleDto;
import uz.pdp.entity.Role;

import java.util.List;

@Service
public interface RoleService {
    void save(RoleDto role);
    void update(Role role);
    void delete(Long id);
    Role getById(Long id);
    List<Role> getAll();
    List<Role> getByPermission(String... permissions);
    Role getByName(String name);
    Role getByCode(String code);
}
