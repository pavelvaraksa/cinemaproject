package by.itacademy.repository.impl;

import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.MovieRepository;
import by.itacademy.domain.Movie;
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
            if (session != null) {
                String hqlQuery = "select u from Movie u";
                return session.createQuery(hqlQuery, Movie.class).list();
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
                throw new RepositoryException("Movie does not exist");
            }
        }
    }

    @Override
    public Movie save(Movie movie) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.save(movie);
            if (movie != null) {
                return movie;
            } else {
                throw new RepositoryException("Movie does not save");
            }
        }
    }

    @Override
    public Movie update(Movie movieId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(movieId);
            transaction.commit();
            if (movieId != null) {
                return movieId;
            } else {
                throw new RepositoryException("Movie does not update");
            }
        }
    }

    @Override
    public Long delete(Movie movieId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(movieId);
            if (movieId != null) {
                return movieId.getId();
            } else {
                throw new RepositoryException("Movie does not delete");
            }
        }
    }
}
