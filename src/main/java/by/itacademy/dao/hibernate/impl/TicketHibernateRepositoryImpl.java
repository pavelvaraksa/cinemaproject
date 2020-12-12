package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.TicketHibernateRepository;
import by.itacademy.domain.hibernate.TicketHibernate;
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
public class TicketHibernateRepositoryImpl implements TicketHibernateRepository {

    private final SessionFactory sessionFactory;

    public TicketHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<TicketHibernate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from TicketHibernate u";
            return session.createQuery(hqlQuery, TicketHibernate.class).list();
        }
    }

    @Override
    public TicketHibernate findById(Long ticketHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(TicketHibernate.class, ticketHibernateId);
        }
    }

    @Override
    public TicketHibernate save(TicketHibernate ticketHibernate) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(ticketHibernate);
            return ticketHibernate;
        }
    }

    @Override
    public TicketHibernate update(TicketHibernate ticketHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(ticketHibernateId);
            transaction.commit();
            return ticketHibernateId;
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
