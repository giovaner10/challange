package br.com.omnilink.challange.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ConnectionSession {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getInstance() {
        return sessionFactory.openSession();
    }

    public void closeSession(Session session) {
        if (Objects.nonNull(session) && session.isOpen()) {
            session.close();
        }
    }

}
