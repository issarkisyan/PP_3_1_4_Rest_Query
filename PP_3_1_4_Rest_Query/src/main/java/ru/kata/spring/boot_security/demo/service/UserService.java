package ru.kata.spring.boot_security.demo.service;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    User findByUsername(String username);

    Object createUser(User user);

    List<User> readListUsers();

    void update(long id, User updatedUser);

    void delete(long id);

    User show(long id);
}