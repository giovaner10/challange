package br.com.omnilink.desafio.repository;

import br.com.omnilink.desafio.model.Costumer;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CostumerRepositoryImpl {
    @Autowired
    private SessionFactory sessionFactory;

    public void save(Costumer vehicle) {
        sessionFactory.openSession().persist(vehicle);
        //Transaction tx = null;
       // tx.commit();
        sessionFactory.close();

    }

}
