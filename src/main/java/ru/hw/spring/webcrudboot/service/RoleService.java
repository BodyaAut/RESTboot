package ru.hw.spring.webcrudboot.service;

import ru.hw.spring.webcrudboot.model.Role;
import ru.hw.spring.webcrudboot.model.User;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleById(long id);

    Role saveRole(Role role);

    void deleteRole(long id);
}
