package service.impl;

import dao.UserDao;
import factory.UserDaoFactory;
import model.User;
import service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private static final UserDao userDao = UserDaoFactory.getUserDaoInstance();

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public Optional<User> getById(Long id) {
        return userDao.getUserById(id);
    }

    @Override
    public void updateUser(User oldUser, User newUser) {
        userDao.updateUser(oldUser, newUser);
    }

    @Override
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
