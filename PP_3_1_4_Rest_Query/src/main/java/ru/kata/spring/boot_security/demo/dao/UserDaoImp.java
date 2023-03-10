package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;
    public void createUser(User user) {
        entityManager.persist(user);

        //entityManager.persist(user.getId());
        //Query query = entityManager.createQuery("insert into users_roles(user_id) VALUE()", User.class);
        //query.setParameter(1, user.getId());
    }

    public void createRole(Role role) {
        entityManager.persist(role);
        //entityManager.persist(role.getId());
        //Query query = entityManager.createQuery("insert into users_roles(role_id) VALUE(role.getId())");
        //query.setParameter(1, role.getId());
    }
/*
    public void addToCommonTable(User user,Role role) {
        long user_id = user.getId();
        long role_id = user.getId();
        entityManager.persist(user_id);
        entityManager.persist(role_id);
    }

 */

    public User findByUsername(String username) {
        return readListUsers().stream().filter(user -> Objects.equals(user.getUsername(), username)).findAny().orElse (null);
    }

    public List<User> readListUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

    public User show(long id) {
        return readListUsers().stream().filter(user -> user.getId() == id).findAny().orElse (null);
    }

    public void update(long id, User updatedUser) {
        User userToBeUpdated = show(id);
        userToBeUpdated.setUsername(updatedUser.getUsername());
        userToBeUpdated.setPassword(updatedUser.getPassword());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        entityManager.merge(userToBeUpdated);
    }

    public void delete(long id) {
        entityManager.remove(show(id));
    }
}
