package dao.impl.hib;

import dao.CodeDao;
import model.Code;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.Optional;

public class CodeDaoHibImpl implements CodeDao {

    private static final Logger logger = Logger.getLogger(CodeDaoHibImpl.class);

    @Override
    public void addCode(Code code) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(code);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error(code + "can't be added in DB", e);
        }
    }

    @Override
    public Optional<Code> getCode(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Code code = session.get(Code.class, id);
            return Optional.of(code);
        } catch (Exception e) {
            logger.error("Try to get code by id was failed!", e);
        }
        return Optional.empty();
    }
}
