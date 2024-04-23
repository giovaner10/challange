package br.com.omnilink.desafio.repository;

import br.com.omnilink.desafio.model.Costumer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.cfg.Configuration;


@Repository
public class CostumerRepositoryImpl {

    @Autowired
    private  SessionFactory sessionFactory;



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

    // You can add other CRUD methods here
}
