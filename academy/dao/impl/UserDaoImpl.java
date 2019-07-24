package mate.academy.dao.impl;

import mate.academy.dao.UserDao;
import mate.academy.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private static List<User> users = new ArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void remove(Optional<User> user) {
        users.remove(user.get());
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        return users.stream().filter(user -> user.getLogin().equals(login)).findFirst();
    }

    @Override
    public String getPassword(User user) {
        return user.getPassword();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return users.stream().filter(user -> user.getId().equals(id)).findFirst();
    }
}
