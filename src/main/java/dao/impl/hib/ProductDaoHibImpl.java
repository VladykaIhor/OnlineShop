package dao.impl.hib;

import dao.ProductDao;
import model.Product;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProductDaoHibImpl implements ProductDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibImpl.class);

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void add(Product product) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(product);
            transaction.commit();
            logger.info("Product " + product + "added to DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("There was an error while trying to add product to DB from hibernate", e);
        }
    }

    @Override
    public List<Product> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Product", Product.class).list();
        } catch (Exception e) {
            logger.error("Failed to get all products from db", e);
        }
        return Collections.emptyList();
    }

    @Override
    public void remove(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.remove(getById(id));
        } catch (Exception e) {
            logger.error("There was an error while removing product from DB", e);
        }
    }

    @Override
    public Optional<Product> getById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Product product = session.get(Product.class, id);
            return Optional.of(product);
        } catch (Exception e) {
            logger.error("There was an error while getting product by id", e);
        }
        return Optional.empty();
    }


    @Override
    public void update(Product oldProduct, Product newProduct) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update("product", newProduct);
            transaction.commit();
            logger.info("User " + oldProduct.getId() + " update in DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error when try update product in DB from hibernate", e);
        }
    }
}
