package by.itacademy.repository.impl;

import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.CinemaRepository;
import by.itacademy.domain.Cinema;
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
public class CinemaRepositoryImpl implements CinemaRepository {

    private final SessionFactory sessionFactory;

    public CinemaRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Cinema> findAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            if (session != null) {
                String hqlQuery = "select u from Cinema u";
                return session.createQuery(hqlQuery, Cinema.class).list();
            } else {
                throw new RepositoryException("No one cinema does not exist");
            }
        }
    }

    @Override
    public Cinema findById(Long cinemaId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Cinema cinemaToFindById = session.find(Cinema.class, cinemaId);
            if (cinemaToFindById != null) {
                return cinemaToFindById;
            } else {
                throw new RepositoryException("Cinema does not exist");
            }
        }
    }

    @Override
    public Cinema save(Cinema cinema) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.save(cinema);
            if (cinema != null) {
                return cinema;
            } else {
                throw new RepositoryException("Cinema does not save");
            }
        }
    }

    @Override
    public Cinema update(Cinema cinemaId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(cinemaId);
            transaction.commit();
            if (cinemaId != null) {
                return cinemaId;
            } else {
                throw new RepositoryException("Cinema does not update");
            }
        }
    }

    @Override
    public Long delete(Cinema cinemaId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(cinemaId);
            if (cinemaId != null) {
                return cinemaId.getId();
            } else {
                throw new RepositoryException("Cinema does not delete");
            }
        }
    }
}
