package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService, UserDetailsService {

    private final UserDao userDao;

    public UserServiceImp(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User loadUserByUsername = userDao.findByUsername(username);

        if (loadUserByUsername == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(loadUserByUsername.getUsername(), loadUserByUsername.getPassword(), mapRolesToAuthorities(loadUserByUsername.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public User findByUsername(String username) {
        return this.userDao.findByUsername(username);
    }
    @Transactional
    public Object createUser(User user) {
        this.userDao.createUser(user);
        return null;
    }
    @Transactional
    public Object createRole(Role role) {
        this.userDao.createRole(role);
        return null;
    }
/*
    @Transactional
    public Object addToCommonTable(User user,Role role) {
        this.userDao.addToCommonTable(user,role);
        return null;
    }

 */

    @Transactional(readOnly = true)
    public List<User> readListUsers() {
        return this.userDao.readListUsers();
    }
    @Transactional
    public void update(long id, User updatedUser) {
        this.userDao.update(id, updatedUser);
    }
    @Transactional
    public void delete(long id) {
        this.userDao.delete(id);
    }
    @Transactional(readOnly = true)
    public User show(long id) {
        return this.userDao.show(id);
    }

}
