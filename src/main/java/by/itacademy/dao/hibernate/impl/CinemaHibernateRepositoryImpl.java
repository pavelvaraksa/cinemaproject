package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.CinemaHibernateRepository;
import by.itacademy.domain.hibernate.CinemaHibernate;
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
public class CinemaHibernateRepositoryImpl implements CinemaHibernateRepository {

    private final SessionFactory sessionFactory;

    public CinemaHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CinemaHibernate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from CinemaHibernate u";
            return session.createQuery(hqlQuery, CinemaHibernate.class).list();
        }
    }

    @Override
    public CinemaHibernate findById(Long cinemaHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(CinemaHibernate.class, cinemaHibernateId);
        }
    }

    @Override
    public CinemaHibernate save(CinemaHibernate cinemaHibernate) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(cinemaHibernate);
            return cinemaHibernate;
        }
    }

    @Override
    public CinemaHibernate update(CinemaHibernate cinemaHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(cinemaHibernateId);
            transaction.commit();
            return cinemaHibernateId;
        }
    }

    @Override
    public Long delete(Long cinemaHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            session.delete(cinemaHibernateId);
            return cinemaHibernateId;
        }
    }
}
