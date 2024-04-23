package br.com.omnilink.desafio.repository.costumer;

import br.com.omnilink.desafio.model.Costumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class CostumerRepositoryImpl implements ICostumerRepository {

    @Autowired
    private  SessionFactory sessionFactory;


    @Override
    public Costumer findById(long id) {
        return null;
    }

    @Override
    public List<Costumer> findAll() {
        return List.of();
    }

    @Override
    public void save(Costumer entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Costumer user) {

    }

    @Override
    public void delete(Costumer user) {

    }

}
