package by.itacademy.repository.impl;

import by.itacademy.domain.Movie;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.MovieRepository;
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
public class MovieRepositoryImpl implements MovieRepository {

    private final SessionFactory sessionFactory;

    public MovieRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Movie> findAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            String moviesToFind = "select u from Movie u";
            if (session != null) {
                return session.createQuery(moviesToFind, Movie.class).list();
            } else {
                throw new RepositoryException("No one movie does not exist");
            }
        }
    }

    @Override
    public Movie findById(Long movieId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Movie movieToFindById = session.find(Movie.class, movieId);
            if (movieToFindById != null) {
                return movieToFindById;
            } else {
                throw new RepositoryException("Movie with id " + movieId + " does not exist");
            }
        }
    }

    @Override
    public Movie save(Movie movie) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Movie movieToSave = session.find(Movie.class, movie);
            if (movieToSave != null) {
                return movieToSave;
            } else {
                throw new RepositoryException("Movie " + movie + " saved");
            }
        }
    }

    @Override
    public Movie update(Movie movieId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Movie movieToUpdate = session.find(Movie.class, movieId);
            if (movieToUpdate != null) {
                return movieToUpdate;
            } else {
                throw new RepositoryException("Movie with id " + movieId + " updated");
            }
        }
    }

    @Override
    public Long delete(Long movieId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(movieId);
            transaction.commit();
            log.info("Movie with id " + movieId + " deleted");
            return movieId;
        }
    }
}
