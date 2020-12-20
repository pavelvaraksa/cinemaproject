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
            String usersToFind = "select u from User u";
            if (session != null) {
                return session.createQuery(usersToFind, User.class).list();
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
                throw new RepositoryException("User with id " + userId + " does not exist");
            }
        }
    }

    @Override
    public User save(User user) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            User userToSave = session.find(User.class, user);
            if (userToSave != null) {
                return userToSave;
            } else {
                throw new RepositoryException("User " + user + " saved");
            }
        }
    }

    @Override
    public User update(User userId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            User userToUpdate = session.find(User.class, userId);
            if (userToUpdate != null) {
                return userToUpdate;
            } else {
                throw new RepositoryException("User with id " + userId + " updated");
            }
        }
    }

    @Override
    public Long delete(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(userId);
            transaction.commit();
            log.info("User with id " + userId + " deleted");
            return userId;
        }
    }
}
