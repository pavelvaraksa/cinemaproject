package by.itacademy.repository.impl;

import by.itacademy.domain.Cinema;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.CinemaRepository;
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
            String cinemasToFind = "select u from Cinema u";
            if (session != null) {
                return session.createQuery(cinemasToFind, Cinema.class).list();
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
                throw new RepositoryException("Cinema with id " + cinemaId + " does not exist");
            }
        }
    }

    @Override
    public Cinema save(Cinema cinema) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Cinema cinemaToSave = session.find(Cinema.class, cinema);
            if (cinemaToSave != null) {
                return cinemaToSave;
            } else {
                throw new RepositoryException("Cinema " + cinema + " saved");
            }
        }
    }

    @Override
    public Cinema update(Cinema cinemaId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Cinema cinemaToUpdate = session.find(Cinema.class, cinemaId);
            if (cinemaToUpdate != null) {
                return cinemaToUpdate;
            } else {
                throw new RepositoryException("Cinema with id " + cinemaId + " updated");
            }
        }
    }

    @Override
    public Long delete(Long cinemaId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(cinemaId);
            transaction.commit();
            log.info("Cinema with id " + cinemaId + " deleted");
            return cinemaId;
        }
    }
}
