package mate.academy.dao;

import mate.academy.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    void add(User user);

    List<User> getAll();

    void remove(Optional<User> user);

    Optional<User> getUserByLogin(String login);

    String getPassword(User user);

    Optional<User> getUserById(Long id);
}
