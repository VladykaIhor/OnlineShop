package mate.academy.utils;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOService {

    private static final Logger logger = Logger.getLogger(DAOService.class);
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/shop";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String USER = "root";
    private static final String PASSWORD = "RPi7L83deA";

    public DAOService() {
        try {
            Class.forName(JDBC_DRIVER).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            logger.error("DAO driver error");
        }
    }

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
        } catch (SQLException e) {
            logger.error("Error while attempting to connect to a DB");
        }
        return null;
    }
}
