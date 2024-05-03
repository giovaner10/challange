package br.com.omnilink.challange.repository.costumer;

import br.com.omnilink.challange.DB.ConnectionSession;
import br.com.omnilink.challange.exception.model.BadRequestException;
import br.com.omnilink.challange.model.Costumer;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CostumerRepository {

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

    @Transactional
    public void update(Costumer costumer) {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            session.beginTransaction();
            session.merge(costumer);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new BadRequestException("Fail to update user");
        } finally {
            connectionSession.closeSession(session);
        }
    }

    @Transactional
    public void delete(Integer id) {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            session.beginTransaction();
            Costumer costumer = session.get(Costumer.class, id);
            session.remove(costumer);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null && session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback();
            }
            throw new BadRequestException("Fail to delete user");
        } finally {
            connectionSession.closeSession(session);
        }
    }

    public Optional<Costumer> findById(Integer id) {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            Costumer customer = session.get(Costumer.class, id);
            return Optional.ofNullable(customer);
        } catch (Exception e) {
            throw new BadRequestException("Failed to retrieve customer due to an unexpected error: " + e.getMessage());
        } finally {
            if (session != null) {
                connectionSession.closeSession(session);
            }
        }
    }

    public List<Costumer> findAll() {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            String hql = "FROM Costumer";
            Query<Costumer> query = session.createQuery(hql, Costumer.class);
            return query.list();
        } catch (Exception e) {
            throw new BadRequestException("Failed to retrieve customers due to an unexpected error: " + e.getMessage());
        } finally {
            if (session != null) {
                connectionSession.closeSession(session);
            }
        }
    }

    public boolean existsByEmailOrCnpj(String email, String cnpj) {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            String hql = "SELECT COUNT(*) FROM Costumer WHERE email = :email OR cnpj = :cnpj";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("email", email);
            query.setParameter("cnpj", cnpj);
            Long count = query.uniqueResult();
            return count != 0;
        } catch (Exception e) {
            throw new BadRequestException("Failed to check existence of customer due to an unexpected error: " + e.getMessage());
        } finally {
            if (session != null) {
                connectionSession.closeSession(session);
            }
        }
    }

    public boolean existsByEmailOrCnpjAndId(String email, String cnpj, Integer id) {
        Session session = null;
        try {
            session = connectionSession.getInstance();
            String hql = "SELECT COUNT(*) FROM Costumer WHERE (email = :email OR cnpj = :cnpj) AND id != :id";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("email", email);
            query.setParameter("cnpj", cnpj);
            query.setParameter("id", id);
            Long count = query.uniqueResult();
            return count > 0;
        } catch (Exception e) {
            throw new BadRequestException("Failed to check existence of customer due to an unexpected error: " + e.getMessage());
        } finally {
            if (session != null) {
                connectionSession.closeSession(session);
            }
        }
    }
}
