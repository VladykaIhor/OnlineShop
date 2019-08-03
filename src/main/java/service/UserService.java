package service;

import model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void add(User user);

    List<User> getAll();

    void remove(Long id);

    Optional<User> findUserByLogin(String login);

    Optional<User> getById(Long id);

    void updateUser(User oldUser, User newUser);

}
