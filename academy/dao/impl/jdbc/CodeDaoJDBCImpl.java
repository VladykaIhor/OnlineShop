package mate.academy.dao.impl.jdbc;

import mate.academy.dao.CodeDao;
import mate.academy.model.Code;
import mate.academy.utils.DAOService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CodeDaoJDBCImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);
    private static final DAOService daoService = new DAOService();
    private static final String SQL_ADD_CODE = "INSERT into code (value, order_id) VALUES (?, ?)";
    private static final String SQL_GET_CODE = "SELECT value FROM code WHERE order_id = ?";

    @Override
    public void addCode(Code code) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_CODE);
            preparedStatement.setString(1, code.getCode());
            preparedStatement.setLong(2, code.getOrderId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while adding code to an order");
        }
    }

    @Override
    public String getCode(Long id) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CODE);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("value");
            }
        } catch (SQLException e) {
            logger.error("Error while getting code");
        }
        return null;
    }
}
