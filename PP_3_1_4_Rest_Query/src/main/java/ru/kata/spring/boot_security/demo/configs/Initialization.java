package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.annotation.PostConstruct;

@Component
public class Initialization {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostConstruct
    public void init() {
        Role adminRole = new Role("ROLE_ADMIN");
        Role userRole = new Role("ROLE_USER");
        User admin = new User("Kolya","$2a$12$ujVULFmqt.wMiCJ.oUT1q.XprwxdB7TMLRc.j6VwmBqblKywGM3PG","star@mail.com");
        User normalUser = new User("Mitya","$2a$12$ujVULFmqt.wMiCJ.oUT1q.XprwxdB7TMLRc.j6VwmBqblKywGM3PG","ruzik@mail.com");
        userService.createUser(admin);
        userService.createUser(normalUser);
        roleService.createRole(adminRole);
        roleService.createRole(userRole);
    }
}

