package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.EventAppRepository;
import by.itacademy.domain.hibernate.EventApp;
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
public class EventAppRepositoryImpl implements EventAppRepository {

    private final SessionFactory sessionFactory;

    public EventAppRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<EventApp> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from EventApp u";
            return session.createQuery(hqlQuery, EventApp.class).list();
        }
    }

    @Override
    public EventApp findById(Long eventHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(EventApp.class, eventHibernateId);
        }
    }

    @Override
    public EventApp save(EventApp eventApp) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(eventApp);
            return eventApp;
        }
    }

    @Override
    public EventApp update(EventApp eventAppId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(eventAppId);
            transaction.commit();
            return eventAppId;
        }
    }

    @Override
    public Long delete(Long eventHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(eventHibernateId);
            transaction.commit();
            return eventHibernateId;
        }
    }
}
