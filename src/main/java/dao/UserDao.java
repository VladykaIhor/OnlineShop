package dao;

import model.User;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    List<User> getAll();

    void remove(Long id);

    Optional<User> getUserByLogin(String login);

    Optional<User> getUserById(Long id);

    void updateUser(User oldUser, User newUser);
}
