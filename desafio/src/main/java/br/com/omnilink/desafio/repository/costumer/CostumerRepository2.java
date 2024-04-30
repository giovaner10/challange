package br.com.omnilink.desafio.repository.costumer;

import br.com.omnilink.desafio.DB.ConnectionSession;
import br.com.omnilink.desafio.exception.BadRequestException;
import br.com.omnilink.desafio.model.Costumer;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CostumerRepository2 {

    @Autowired
    private ConnectionSession connectionSession;

    @Transactional
    public void save(Costumer costumer) {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            session.beginTransaction();
            session.persist(costumer);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new BadRequestException("Fail to save user");

        } finally {
            connectionSession.closeSession(session);
        }
    }
}
