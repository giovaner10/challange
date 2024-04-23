package br.com.omnilink.desafio.DB;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConnectionSession {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getInstance() {
        return sessionFactory.openSession();
    }
}
