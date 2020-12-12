package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.EventHibernateRepository;
import by.itacademy.domain.hibernate.EventHibernate;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@Log4j2
public class EventHibernateRepositoryImpl implements EventHibernateRepository {

    private final SessionFactory sessionFactory;

    public EventHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<EventHibernate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from EventHibernate u";
            return session.createQuery(hqlQuery, EventHibernate.class).list();
        }
    }

    @Override
    public EventHibernate findById(Long eventHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(EventHibernate.class, eventHibernateId);
        }
    }

    @Override
    public EventHibernate save(EventHibernate eventHibernate) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(eventHibernate);
            return eventHibernate;
        }
    }

    @Override
    public EventHibernate update(EventHibernate eventHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(eventHibernateId);
            transaction.commit();
            return eventHibernateId;
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
