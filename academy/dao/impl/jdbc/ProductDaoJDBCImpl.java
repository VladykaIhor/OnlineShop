package mate.academy.dao.impl.jdbc;

import mate.academy.dao.ProductDao;
import mate.academy.model.Product;
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

public class ProductDaoJDBCImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(ProductDaoJDBCImpl.class);
    private static final DAOService daoService = new DAOService();
    private static final String SQL_ADD_PRODUCT = "INSERT INTO products (name, " +
            "description, price) VALUES (?, ?, ?)";
    private static final String SQL_DELETE_PRODUCT = "DELETE FROM products WHERE id = (?)";
    private static final String SQL_GET_BY_ID = "SELECT * FROM products WHERE id = (?)";
    private static final String SQL_GET_ALL = "SELECT * FROM PRODUCTS";
    private static final String SQL_UPDATE_PRODUCT = "UPDATE products SET name = (?)," +
            "description = (?), price = (?) WHERE id = (?)";

    @Override
    public List<Product> getAll() {
        List<Product> productList = new ArrayList<>();
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                product.setId(resultSet.getLong("id"));
                productList.add(product);
            }
            return productList;
        } catch (SQLException e) {
            logger.error("An error happened while getting all " +
                    "users from the list, beda-pechal((");
        }
        return Collections.emptyList();
    }

    @Override
    public void remove(Long id) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_PRODUCT);
            preparedStatement.setString(1, String.valueOf(id));
            preparedStatement.executeUpdate();
            logger.info("Product " + getById(id) + " was removed from DB");
        } catch (SQLException e) {
            logger.error("There was an error while removing product from DB");
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_BY_ID);
            preparedStatement.setString(1, String.valueOf(id));
            return getProductFromResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            logger.error("There was an error while removing product from DB");
        }
        return Optional.empty();
    }

    @Override
    public void add(Product product) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setString(3, String.valueOf(product.getPrice()));

            preparedStatement.executeUpdate();
            logger.info("product " + product.getName() + "was added to DB");
        } catch (SQLException e) {
            logger.error("There was an error while adding product to DB");
        }
    }

    @Override
    public void update(Product oldProduct, Product newProduct) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_PRODUCT);
            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setString(2, newProduct.getDescription());
            preparedStatement.setString(3, String.valueOf(newProduct.getPrice()));
            preparedStatement.setString(4, String.valueOf(oldProduct.getId()));
            preparedStatement.executeUpdate();
            logger.info("The product " + oldProduct.getName() + "was edited");
        } catch (SQLException e) {
            logger.error("Error while editing a product's field, check it!");
        }
    }

    private Optional<Product> getProductFromResultSet(ResultSet resultSet) {
        try {
            resultSet.next();
            Product product = new Product(resultSet.getString("name"),
                    resultSet.getString("description"),
                    resultSet.getDouble("price"));
            product.setId(resultSet.getLong("id"));
            return Optional.of(product);
        } catch (SQLException e) {
            logger.error("Error while building a product form resultSet", e);
        }
        return Optional.empty();
    }
}
