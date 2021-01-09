package by.itacademy.repository.impl;

import by.itacademy.repository.MovieRepository;
import by.itacademy.domain.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class MovieRepositoryImpl implements MovieRepository {

    private final SessionFactory sessionFactory;

    public MovieRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Cacheable("movies")
    @Query(value = "select m from Movie m")
    @Override
    public List<Movie> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from Movie u";
            return session.createQuery(hqlQuery, Movie.class).list();
        }
    }

    @Override
    public Movie findById(Long movieId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Movie.class, movieId);
        }
    }

    @Override
    public Movie save(Movie movie) {
        try (Session session = sessionFactory.openSession()) {
            session.save(movie);
            return movie;
        }
    }

    @Override
    public Movie update(Movie movieId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(movieId);
            transaction.commit();
            return movieId;
        }
    }

    @Override
    public Movie delete(Long movieId) {
        try (Session session = sessionFactory.openSession()) {
            Movie movieDeleteById = session.find(Movie.class, movieId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(movieDeleteById);
            transaction.commit();
            return movieDeleteById;
        }
    }
}
