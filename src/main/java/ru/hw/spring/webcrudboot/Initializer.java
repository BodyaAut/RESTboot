package ru.hw.spring.webcrudboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.hw.spring.webcrudboot.model.Role;
import ru.hw.spring.webcrudboot.model.User;
import ru.hw.spring.webcrudboot.service.RoleService;
import ru.hw.spring.webcrudboot.service.UserService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Component
public class Initializer implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    public Initializer (UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) {

        Role adminRole = new Role();
        adminRole.setRole("ROLE_ADMIN");
        roleService.saveRole(adminRole);

        Role userRole = new Role();
        userRole.setRole("ROLE_USER");
        roleService.saveRole(userRole);

        User adminUser = User.builder().name("Jack")
                                       .age(20)
                                       .email("jack@mail.com")
                                       .password("$2a$12$ioTpDYwiFOlnsbcSx7vMK.oigLez3dwiyEGYIqV.j2qkA9NU3bYza")
                                       .build();
        adminUser.setRoles(new HashSet<>(Arrays.asList(adminRole, userRole)));
        userService.saveUser(adminUser);

        User commonUser = User.builder().name("John")
                                        .age(23)
                                        .email("john@mail.com")
                                        .password("$2a$12$lXyp9EfTBJTGU2UtlPD0.ecuoFfuRgKGc5DbK8pc7odirgoDK0vj.")
                                        .build();
        commonUser.setRoles(new HashSet<>(List.of(userRole)));
        userService.saveUser(commonUser);
    }
}