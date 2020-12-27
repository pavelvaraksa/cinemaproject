package by.itacademy.repository.impl;

import by.itacademy.repository.UserRepository;
import by.itacademy.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class UserRepositoryImpl implements UserRepository {

    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from User u";
            return session.createQuery(hqlQuery, User.class).list();
        }
    }

    @Override
    public User findById(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(User.class, userId);
        }
    }

    @Override
    public User save(User user) {
        try (Session session = sessionFactory.openSession()) {
            session.save(user);
            return user;
        }
    }

    @Override
    public User update(User userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(userId);
            transaction.commit();
            return userId;
        }
    }

    @Override
    public User delete(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            User userDeleteById = session.find(User.class, userId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(userDeleteById);
            transaction.commit();
            return userDeleteById;
        }
    }
}
