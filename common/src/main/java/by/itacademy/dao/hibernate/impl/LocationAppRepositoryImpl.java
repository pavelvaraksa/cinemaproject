package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.LocationAppRepository;
import by.itacademy.domain.hibernate.LocationApp;
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
public class LocationAppRepositoryImpl implements LocationAppRepository {

    private final SessionFactory sessionFactory;

    public LocationAppRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<LocationApp> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from LocationApp u";
            return session.createQuery(hqlQuery, LocationApp.class).list();
        }
    }

    @Override
    public LocationApp findById(Long locationHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(LocationApp.class, locationHibernateId);
        }
    }

    @Override
    public LocationApp save(LocationApp locationApp) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(locationApp);
            return locationApp;
        }
    }

    @Override
    public LocationApp update(LocationApp locationAppId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(locationAppId);
            transaction.commit();
            return locationAppId;
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
