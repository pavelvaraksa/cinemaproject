package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.LocationHibernateRepository;
import by.itacademy.domain.hibernate.LocationHibernate;
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
public class LocationHibernateRepositoryImpl implements LocationHibernateRepository {

    private final SessionFactory sessionFactory;

    public LocationHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<LocationHibernate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from LocationHibernate u";
            return session.createQuery(hqlQuery, LocationHibernate.class).list();
        }
    }

    @Override
    public LocationHibernate findById(Long locationHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(LocationHibernate.class, locationHibernateId);
        }
    }

    @Override
    public LocationHibernate save(LocationHibernate locationHibernate) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(locationHibernate);
            return locationHibernate;
        }
    }

    @Override
    public LocationHibernate update(LocationHibernate locationHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(locationHibernateId);
            transaction.commit();
            return locationHibernateId;
        }
    }

    @Override
    public Long delete(Long locationHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(locationHibernateId);
            transaction.commit();
            return locationHibernateId;
        }
    }
}
