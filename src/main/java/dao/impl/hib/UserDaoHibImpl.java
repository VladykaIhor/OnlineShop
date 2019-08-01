package dao.impl.hib;

import dao.UserDao;
import model.User;
import org.apache.log4j.Logger;
import utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class UserDaoHibImpl implements UserDao {

    private static final Logger logger = Logger.getLogger(UserDaoHibImpl.class);

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void add(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            logger.info("User " + user + "added to DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("There was an error while trying to add user to DB from hibernate", e);
        }
    }

    @Override
    public List<User> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        }
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User where login = :login");
            query.setParameter("login", login);
            User user = (User) query.getSingleResult();
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("There was an error while getting user by login", e);
        }
        return Optional.empty();
    }

    @Override
    public void remove(Long id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            if (getUserById(id).isPresent()) {
                session.delete(getUserById(id).get());
            }
            transaction.commit();
            logger.info("User " + getUserById(id) + " deleted from DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error when try delete user from DB from hibernate", e);
        }
    }

    @Override
    public Optional<User> getUserById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            return Optional.of(user);
        } catch (Exception e) {
            logger.error("There was an error while getting user by id", e);
        }
        return Optional.empty();
    }

    @Override
    public void updateUser(User oldUser, User newUser) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update("user", newUser);
            transaction.commit();
            logger.info("User " + oldUser.getId() + " update in DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Error when try update user in DB from hibernate", e);
        }
    }
}
