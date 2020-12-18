package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.CinemaAppRepository;
import by.itacademy.domain.hibernate.CinemaApp;
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
public class CinemaAppRepositoryImpl implements CinemaAppRepository {

    private final SessionFactory sessionFactory;

    public CinemaAppRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CinemaApp> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from CinemaApp u";
            return session.createQuery(hqlQuery, CinemaApp.class).list();
        }
    }

    @Override
    public CinemaApp findById(Long cinemaHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(CinemaApp.class, cinemaHibernateId);
        }
    }

    @Override
    public CinemaApp save(CinemaApp cinemaApp) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(cinemaApp);
            return cinemaApp;
        }
    }

    @Override
    public CinemaApp update(CinemaApp cinemaAppId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(cinemaAppId);
            transaction.commit();
            return cinemaAppId;
        }
    }

    @Override
    public Long delete(Long cinemaHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(cinemaHibernateId);
            transaction.commit();
            return cinemaHibernateId;
        }
    }
}
