package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.dto.PermissionDto;
import uz.pdp.dto.RoleDto;
import uz.pdp.entity.Permission;
import uz.pdp.entity.Role;

import java.util.List;

@Service
public interface PermissionService {
    Permission save(PermissionDto permission);
    void update(Permission permission);
    void delete(Long id);
    Permission getById(Long id);
    List<Permission> getAll();
    Permission getByName(String name);
    Permission getByCode(String code);
}
