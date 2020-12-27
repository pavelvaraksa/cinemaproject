package by.itacademy.repository.impl;

import by.itacademy.repository.CinemaRepository;
import by.itacademy.domain.Cinema;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class CinemaRepositoryImpl implements CinemaRepository {

    private final SessionFactory sessionFactory;

    public CinemaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Cinema> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from Cinema u";
            return session.createQuery(hqlQuery, Cinema.class).list();
        }
    }

    @Override
    public Cinema findById(Long cinemaId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Cinema.class, cinemaId);
        }
    }

    @Override
    public Cinema save(Cinema cinema) {
        try (Session session = sessionFactory.openSession()) {
            session.save(cinema);
            return cinema;
        }
    }

    @Override
    public Cinema update(Cinema cinemaId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(cinemaId);
            transaction.commit();
            return cinemaId;
        }
    }

    @Override
    public Cinema delete(Long cinemaId) {
        try (Session session = sessionFactory.openSession()) {
            Cinema cinemaDeleteById = session.find(Cinema.class, cinemaId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(cinemaDeleteById);
            transaction.commit();
            return cinemaDeleteById;
        }
    }
}
