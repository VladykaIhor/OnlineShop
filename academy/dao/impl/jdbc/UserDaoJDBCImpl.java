package mate.academy.dao.impl.jdbc;

import mate.academy.dao.UserDao;
import mate.academy.model.User;
import mate.academy.utils.DAOService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class UserDaoJDBCImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);
    private static final DAOService daoService = new DAOService();
    private static final String SQL_ADD_USER = "INSERT INTO users (login, email, password, role) "
            + "VALUES(?, ?, ?, ?)";
    private static final String SQL_GET_USERS = "SELECT * FROM users";
    private static final String SQL_REMOVE_USER = "DELETE FROM users WHERE id = (?)";
    private static final String SQL_GET_USER_BY_LOGIN = "SELECT * FROM users WHERE login = (?)";
    private static final String SQL_GET_USER_BY_ID = "SELECT * FROM users WHERE id = (?)";
    private static final String SQL_UPDATE_USER = "UPDATE users SET login = (?), email = (?)," +
            "password = (?) WHERE id = (?)";

    @Override
    public void add(User user) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_USER);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.executeUpdate();
            logger.info("User " + user + " added to DB");
        } catch (SQLException e) {
            logger.error("Error while adding user to DB");
        }
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(resultSet.getString("login"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("role"));
                user.setId(Long.valueOf(resultSet.getString("id")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.error("Error  while getting all users from DB");
        }
        return Collections.emptyList();
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_REMOVE_USER);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while removing a user in DB");
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement =
                    connection.prepareStatement(SQL_GET_USER_BY_LOGIN);
            preparedStatement.setString(1, login);
            return getUserFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            logger.error("Error while getting user bu login", e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_USER_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            return getUserFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            logger.error("Error while getting User by id");
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserFromResultSet(ResultSet resultSet) {
        try {
            resultSet.next();
            User user = new User(resultSet.getString("email"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("role"));
            user.setId(resultSet.getLong("id"));
            return Optional.of(user);
        } catch (SQLException e) {
            logger.error("Error while building a user form resultSet", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User oldUser, User newUser) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            preparedStatement.setString(1, newUser.getLogin());
            preparedStatement.setString(2, newUser.getEmail());
            preparedStatement.setString(3, newUser.getPassword());
            preparedStatement.setString(4, String.valueOf(oldUser.getId()));
            preparedStatement.executeUpdate();
            logger.info("User " + oldUser.getId() + " update in DB");
        } catch (SQLException e) {
            logger.error("Error while updating the user in DB", e);
        }
    }
}
