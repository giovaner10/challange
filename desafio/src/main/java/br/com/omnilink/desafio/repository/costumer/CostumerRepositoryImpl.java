package br.com.omnilink.desafio.repository.costumer;

import br.com.omnilink.desafio.DB.ConnectionSession;
import br.com.omnilink.desafio.model.Costumer;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class CostumerRepositoryImpl implements IBaseRepository<Costumer> {

    @Autowired
    private ConnectionSession connectionSession;

    @Override
    public Costumer findById(Integer id) {
        try (Session session = connectionSession.getInstance()) {
            return session.get(Costumer.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Costumer> findAll() {
        try (Session session = connectionSession.getInstance()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Costumer> query = builder.createQuery(Costumer.class);
            query.from(Costumer.class);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void save(Costumer entity) {
        Transaction transaction = null;
        try (Session session = connectionSession.getInstance()) {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void update(Costumer costumer) {
        Transaction transaction = null;
        try (Session session = connectionSession.getInstance()) {
            transaction = session.beginTransaction();
            session.merge(costumer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Costumer costumer) {
        Transaction transaction = null;
        try (Session session = connectionSession.getInstance()) {
            transaction = session.beginTransaction();
            session.remove(costumer);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

    }

}
