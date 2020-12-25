package by.itacademy.repository.impl;

import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.UserRepository;
import by.itacademy.domain.User;
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
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            if (session != null) {
                String hqlQuery = "select u from User u";
                return session.createQuery(hqlQuery, User.class).list();
            } else {
                throw new RepositoryException("No one user does not exist");
            }
        }
    }

    @Override
    public User findById(Long userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            User userToFindById = session.find(User.class, userId);
            if (userToFindById != null) {
                return userToFindById;
            } else {
                throw new RepositoryException("User does not exist");
            }
        }
    }

    @Override
    public User save(User user) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
            if (user != null) {
                return user;
            } else {
                throw new RepositoryException("User does not save");
            }
        }
    }

    @Override
    public User update(User userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(userId);
            transaction.commit();
            if (userId != null) {
                return userId;
            } else {
                throw new RepositoryException("User does not update");
            }
        }
    }

    @Override
    public Long delete(User userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(userId);
            if (userId != null) {
                return userId.getId();
            } else {
                throw new RepositoryException("User does not delete");
            }
        }
    }
}
