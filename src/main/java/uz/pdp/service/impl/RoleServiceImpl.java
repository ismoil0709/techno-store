package uz.pdp.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.dto.PermissionDto;
import uz.pdp.dto.RoleDto;
import uz.pdp.entity.Permission;
import uz.pdp.entity.Role;
import uz.pdp.repository.RoleRepo;
import uz.pdp.service.PermissionService;
import uz.pdp.service.RoleService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepo roleRepo;
    private final PermissionService permissionService;

    @Override
    public void save(RoleDto role) {
        if (role.name() == null || role.name().isEmpty() || role.name().isBlank())
            throw new IllegalArgumentException("Role name cannot be null, empty, or blank");
        if (role.code() == null || role.code().isEmpty() || role.code().isBlank())
            throw new IllegalArgumentException("Role code cannot be null, empty, or blank");
        if (role.permissions() == null || role.permissions().length == 0)
            throw new IllegalArgumentException("Role must have at least one permission");

        Set<Permission> permissionList = new HashSet<>();
        for (String permission : role.permissions()) {
            Permission existingPermission = permissionService.getByName(permission);
            if (existingPermission == null) {
                existingPermission = permissionService.save(new PermissionDto(permission, permission.toUpperCase()));
            }
            permissionList.add(existingPermission);
        }
        roleRepo.save(Role.builder()
                .name(role.name())
                .code(role.code())
                .permissions(permissionList)
                .build()
        );
    }

    @Override
    public void update(Role role) {
        Optional<Role> existingRole = roleRepo.findById(role.getId());
        if (existingRole.isEmpty()) {
            throw new IllegalArgumentException("Role not found");
        }
        Role role1 = existingRole.get();
        roleRepo.save(
                Role.builder()
                        .id(role.getId())
                        .name(Objects.requireNonNullElse(role.getName(),role1.getName()))
                        .code(Objects.requireNonNullElse(role.getCode(),role1.getCode()))
                        .permissions(Objects.requireNonNullElse(role.getPermissions(),role1.getPermissions()))
                        .build()
        );
    }

    @Override
    public void delete(Long id) {
        Optional<Role> byId = roleRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Role not found");
        roleRepo.delete(byId.get());
    }

    @Override
    public Role getById(Long id) {
        Optional<Role> byId = roleRepo.findById(id);
        if (byId.isEmpty())
            throw new IllegalArgumentException("Role not found");
        return byId.get();
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        roleRepo.findAll().iterator().forEachRemaining(roles::add);
        if (roles.isEmpty())
            throw new IllegalArgumentException("No roles found");
        return roles;
    }

    @Override
    public List<Role> getByPermission(String... permissions) {
        Set<Permission> permissionList = new HashSet<>();
        for (String permission : permissions) {
            Permission existingPermission = permissionService.getByName(permission);
            if (existingPermission != null) {
                permissionList.add(existingPermission);
            }
        }
        return roleRepo.getRolesByPermissions(permissionList);
    }

    @Override
    public Role getByName(String name) {
        if (name == null || name.isBlank() || name.isEmpty())
            throw new IllegalArgumentException("Role name cannot be null, empty, or blank");
        Role roleByName = roleRepo.getRoleByName(name);
        if (roleByName == null)
            throw new IllegalArgumentException("Role not found");
        return roleByName;
    }

    @Override
    public Role getByCode(String code) {
        if (code == null || code.isEmpty() || code.isBlank())
            throw new IllegalArgumentException("Role code cannot be null, empty, or blank");
        Role roleByCode = roleRepo.getRoleByCode(code);
        if (roleByCode == null)
            throw new IllegalArgumentException("Role not found");
        return roleByCode;
    }
}
