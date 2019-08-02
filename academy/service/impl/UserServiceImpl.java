package mate.academy.service.impl;

import mate.academy.dao.UserDao;
import mate.academy.factory.UserDaoFactory;
import mate.academy.model.User;
import mate.academy.service.UserService;

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
    public void remove(Long id) {
        userDao.remove(id);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
