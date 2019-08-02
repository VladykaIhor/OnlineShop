package mate.academy.dao.impl.jdbc;

import mate.academy.dao.OrderDao;
import mate.academy.model.Order;
import mate.academy.model.User;
import mate.academy.utils.DAOService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDaoJDBCImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJDBCImpl.class);
    private static final DAOService daoService = new DAOService();
    private static final String SQL_ADD_ORDER = "INSERT INTO order (name, surname, " +
            "email, country, state, city, phoneNumber, zip)" +
            "VALUES(?, ?, ?, ? ,?, ?, ?, ?)";
    private static final String SQL_GET_ORDER_ID_BY_USER = "SELECT `order`.id" +
            " FROM `order` INNER JOIN " +
            "cart ON `order`.cart_id = cart.id WHERE user_id = (?)";

    @Override
    public void addOrder(Order order) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_ORDER);
            preparedStatement.setString(1, order.getName());
            preparedStatement.setString(2, order.getSurname());
            preparedStatement.setString(3, order.getEmail());
            preparedStatement.setString(4, order.getCountry());
            preparedStatement.setString(5, order.getState());
            preparedStatement.setString(6, order.getCity());
            preparedStatement.setString(7, order.getPhoneNumber());
            preparedStatement.setString(8, order.getZip());
            preparedStatement.executeUpdate();
            logger.info("Order " + order.toString() + " added to DB");
        } catch (SQLException e) {
            logger.error("There was an error while adding an order to DB");
        }
    }

    @Override
    public Long getOrderIdByUser(User user) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement(SQL_GET_ORDER_ID_BY_USER);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            logger.error("There was an error while getting getting orderID", e);
        }
        return null;
    }
}
