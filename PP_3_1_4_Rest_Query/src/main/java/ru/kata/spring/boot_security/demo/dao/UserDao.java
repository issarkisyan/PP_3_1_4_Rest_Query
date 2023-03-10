package ru.kata.spring.boot_security.demo.dao;



import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;

public interface UserDao {
    void createUser(User user);

    void createRole(Role role);
/*
    void addToCommonTable(User user,Role role);
*/
    User findByUsername(String username);

    List<User> readListUsers();

    void update(long id, User updatedUser);

    void delete(long id);

    User show(long id);
}
