package mate.academy.dao.impl.jdbc;

import mate.academy.dao.CartDao;
import mate.academy.model.Cart;
import mate.academy.model.Product;
import mate.academy.model.User;
import mate.academy.utils.DAOService;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CartDaoJDBCImpl implements CartDao {

    private static final Logger logger = Logger.getLogger(UserDaoJDBCImpl.class);
    private static final DAOService daoService = new DAOService();
    private static final String SQL_ADD_PRODUCT_TO_CART = "INSERT INTO cart_product(product_id, " +
            "cart_id) values (?, ?)";
    private static final String SQL_ADD_CART = "INSERT into cart(user_id) VALUES (?)";
    private static final String SQL_GET_ALL_PRODUCTS = "SELECT p.id, p.name," +
            " p.description, p.price " +
            " FROM products p" +
            " INNER JOIN cart_product cp on p.id = cp.product_id\n " +
            " INNER JOIN cart c on cp.cart_id = c.id\n" +
            " WHERE c.user_id;";
    private static final String SQL_SIZE_CART = "SELECT COUNT(*) FROM cart_product WHERE " +
            "cart_id = ?";
    private static final String SQL_GET_CART_ID_BY_USER = "SELECT id from cart WHERE user_id =?";
    private static final String SQL_RESET_BASKET = "DELETE cart.*, product_cart.* FROM cart " +
            "INNER JOIN cart_basket ON cart.user_id =\n product_cart.user_id " +
            "WHERE cart.user_id = (?)";



    @Override
    public void createCart(User user) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_CREATE_CART);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
            logger.info("The shopping cart was created for user with " + user.getId() + "id");
        } catch (SQLException e) {
            logger.error("There was an error while creating a shopping cart");
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_PRODUCTS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product cartProduct = new Product(resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getDouble("price"));
                cartProduct.setId(resultSet.getLong("id"));
                products.add(cartProduct);
                logger.info("Got the list of products in da cart");
            }
        } catch (SQLException e) {
            logger.error("Failed to get list of products from cart");
        }
        return products;
    }


    @Override
    public Long getCartByUser(User user) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_CART_ID_BY_USER);
            preparedStatement.setLong(1, user.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
        } catch (SQLException e) {
            logger.error("There was an error while getting a cart by userId", e);
        }
        return null;
    }

    @Override
    public void addProductToCart(User user, Product product) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_PRODUCT_TO_CART);
            preparedStatement.setLong(1, product.getId());
            preparedStatement.setLong(2, getCartByUser(user));
            preparedStatement.execute();
            logger.info("product " + product.getName() + "was added to a shopping cart");
        } catch (SQLException e) {
            logger.error("There was an error while adding product to a cart");
        }
    }

    @Override
    public void resetCart(User user) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_RESET_BASKET);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Error while trying to reset the shopping cart in DB", e);
        }
    }

    @Override
    public int getSizeOfACart(Cart cart) {
        try (Connection connection = daoService.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_SIZE_CART);
            preparedStatement.setString(1, String.valueOf(cart.getId()));
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            logger.error("Failed to get cart size");
        }
        return 0;
    }
}
