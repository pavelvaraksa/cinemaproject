package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.TicketAppRepository;
import by.itacademy.domain.hibernate.TicketApp;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@Log4j
public class TicketAppRepositoryImpl implements TicketAppRepository {

    private final SessionFactory sessionFactory;

    public TicketAppRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TicketApp> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from TicketApp u";
            return session.createQuery(hqlQuery, TicketApp.class).list();
        }
    }

    @Override
    public TicketApp findById(Long ticketHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(TicketApp.class, ticketHibernateId);
        }
    }

    @Override
    public TicketApp save(TicketApp ticketApp) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(ticketApp);
            return ticketApp;
        }
    }

    @Override
    public TicketApp update(TicketApp ticketAppId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(ticketAppId);
            transaction.commit();
            return ticketAppId;
        }
    }

    @Override
    public Long delete(Long ticketHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(ticketHibernateId);
            transaction.commit();
            return ticketHibernateId;
        }
    }
}
