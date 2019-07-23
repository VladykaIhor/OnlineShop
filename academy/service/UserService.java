package mate.academy.service;

import mate.academy.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);

    List<User> getAll();



    void remove(Optional<User> user);

    Optional<User> findUserByLogin(String login);

    Optional<User> getById(Long id);

    String getPassword(User user);
}
