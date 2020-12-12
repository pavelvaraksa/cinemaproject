package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.MovieHibernateRepository;
import by.itacademy.domain.hibernate.MovieHibernate;
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
public class MovieHibernateRepositoryImpl implements MovieHibernateRepository {

    private final SessionFactory sessionFactory;

    public MovieHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieHibernate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from MovieHibernate u";
            return session.createQuery(hqlQuery, MovieHibernate.class).list();
        }
    }

    @Override
    public MovieHibernate findById(Long movieHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(MovieHibernate.class, movieHibernateId);
        }
    }

    @Override
    public MovieHibernate save(MovieHibernate movieHibernate) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(movieHibernate);
            return movieHibernate;
        }
    }

    @Override
    public MovieHibernate update(MovieHibernate movieHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(movieHibernateId);
            transaction.commit();
            return movieHibernateId;
        }
    }

    @Override
    public Long delete(Long movieHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(movieHibernateId);
            transaction.commit();
            return movieHibernateId;
        }
    }
}
