package ru.hw.spring.webcrudboot.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hw.spring.webcrudboot.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
