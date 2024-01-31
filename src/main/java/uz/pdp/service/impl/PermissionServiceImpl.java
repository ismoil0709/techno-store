package uz.pdp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.PermissionDto;
import uz.pdp.entity.Permission;
import uz.pdp.repository.PermissionRepo;
import uz.pdp.service.PermissionService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {
    private final PermissionRepo permissionRepo;
    @Override
    public Permission save(PermissionDto permission) {
        if (permission.name() == null || permission.name().isBlank() || permission.name().isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        if (permission.code() == null || permission.code().isBlank() || permission.code().isEmpty())
            throw new IllegalArgumentException("Code cannot be null or empty");
        return permissionRepo.save(
                Permission.builder()
                        .name(permission.name())
                        .code(permission.code())
                        .build()
        );
    }

    @Override
    public void update(Permission permission) {
        if (permission == null || permission.getId() == null)
            throw new IllegalArgumentException("Permission cannot be null or have null id");
        Optional<Permission> byId = permissionRepo.findById(permission.getId());
        if (byId.isEmpty())
            throw new NoSuchElementException("Permission not found");
        Permission permission1 = byId.get();
        permissionRepo.save(
                Permission.builder()
                        .id(permission.getId())
                        .name(Objects.requireNonNullElse(permission.getName(),permission1.getName()))
                        .code(Objects.requireNonNullElse(permission.getCode(),permission1.getCode()))
                        .build()
        );
    }

    @Override
    public void delete(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null");
        Optional<Permission> byId = permissionRepo.findById(id);
        if (byId.isEmpty())
            throw new NoSuchElementException("Permission not found");
        permissionRepo.delete(byId.get());
    }

    @Override
    public Permission getById(Long id) {
        if (id == null)
            throw new IllegalArgumentException("Id cannot be null");
        Optional<Permission> byId = permissionRepo.findById(id);
        if (byId.isEmpty())
            throw new NoSuchElementException("Permission not found");
        return byId.get();
    }

    @Override
    public List<Permission> getAll() {
        List<Permission> permissions = new ArrayList<>();
        permissionRepo.findAll().iterator().forEachRemaining(permissions::add);
        if (permissions.isEmpty())
            throw new NoSuchElementException("No permissions found");
        return permissions;
    }

    @Override
    public Permission getByName(String name) {
        if (name == null || name.isBlank() || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null or empty");
        Permission permissionByName = permissionRepo.getPermissionByName(name);
        if (permissionByName == null)
            throw new NoSuchElementException("Permission not found");
        return permissionByName;
    }

    @Override
    public Permission getByCode(String code) {
        if (code == null || code.isBlank() || code.isEmpty())
            throw new IllegalArgumentException("Code cannot be null or empty");
        Permission permissionByCode = permissionRepo.getPermissionByCode(code);
        if (permissionByCode == null)
            throw new NoSuchElementException("Permission not found");
        return permissionByCode;
    }
}
