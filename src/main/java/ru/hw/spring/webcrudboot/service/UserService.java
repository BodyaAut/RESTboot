package ru.hw.spring.webcrudboot.service;

import ru.hw.spring.webcrudboot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(long id);

    User getUserByEmail(String email);

    User saveUser(User user);

    void deleteUser(long id);

}
