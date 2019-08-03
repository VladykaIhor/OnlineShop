package dao.impl.hib;

import dao.CartDao;
import model.Cart;
import model.Product;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CartDaoHibImpl implements CartDao {

    private static final Logger logger = Logger.getLogger(CartDaoHibImpl.class);

    @Override
    public void createCart(User user) {
        Transaction transaction = null;
        Cart cart = new Cart(user, new ArrayList<>());
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(cart);
            transaction.commit();
            logger.info("Created a shopping cart for " + user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(cart + "can't be updated in DB", e);
        }
    }

    @Override
    public Optional<Cart> getCartByUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Cart where user =: user");
            query.setParameter("user", user);
            List<Cart> list = query.list();
            if (list.size() != 0) {
                return Optional.ofNullable(list.get(list.size() - 1));
            }
        } catch (Exception e) {
            logger.error("Error when can try to get basket for user " + user, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getProducts() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Product where id =: id");
            List<Product> list = query.list();
            return list;
        } catch (Exception e) {
            logger.error("Failed to get all products", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void addProductToCart(User user, Product product) {
        Transaction transaction = null;
        Cart cart = getCartByUser(user).get();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            cart.setProducts(product);
            session.update(cart);
            transaction.commit();
            logger.info("Product for user basket " + user + " added to DB");
        } catch (Exception e) {
            logger.error("Failed to add product to basket");
        }
    }

    @Override
    public void resetCart(User user) {
        Transaction transaction = null;
        Cart cart = getCartByUser(user).get();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.remove(cart);
            transaction.commit();
            logger.info("Basket for user " + user + " reset from DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error when try remove basket for user in DB from hibernate", e);
        }
    }

    @Override
    public int getSizeOfACart(Cart cart) {
        return cart.getSizeOfCart();
    }

    @Override
    public boolean checkCartByUser(User user) {
        return false;
    }

    @Override
    public double getSumOfOrderInCart(User user) {
        return 0;
    }
}
