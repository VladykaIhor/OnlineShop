package dao.impl.hib;

import dao.OrderDao;
import model.Order;
import model.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Optional;

public class OrderDaoHibImpl implements OrderDao {

    private static final Logger logger = Logger.getLogger(OrderDaoHibImpl.class);

    @Override
    public void addOrder(Order order) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(order + "order can't be added to DB");
        }
    }

    @Override
    public Optional<Order> getLastOrderForUser(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("from Order where Order.id = :user order by id desc");
            query.setParameter("user", user);
            query.setMaxResults(1);
            Order order = (Order) query.uniqueResult();
            return Optional.of(order);
        } catch (Exception e) {
            logger.error("Try to get basket for user " + user + " was failed!", e);
        }
        return Optional.empty();
    }
}
