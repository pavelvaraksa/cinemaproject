package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.MovieAppRepository;
import by.itacademy.domain.hibernate.MovieApp;
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
public class MovieAppRepositoryImpl implements MovieAppRepository {

    private final SessionFactory sessionFactory;

    public MovieAppRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieApp> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from MovieApp u";
            return session.createQuery(hqlQuery, MovieApp.class).list();
        }
    }

    @Override
    public MovieApp findById(Long movieHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(MovieApp.class, movieHibernateId);
        }
    }

    @Override
    public MovieApp save(MovieApp movieApp) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(movieApp);
            return movieApp;
        }
    }

    @Override
    public MovieApp update(MovieApp movieAppId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(movieAppId);
            transaction.commit();
            return movieAppId;
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
