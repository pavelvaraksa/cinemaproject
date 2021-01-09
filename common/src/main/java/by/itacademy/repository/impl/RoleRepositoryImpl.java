package by.itacademy.repository.impl;

import by.itacademy.domain.UserRole;
import by.itacademy.repository.RoleRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class RoleRepositoryImpl implements RoleRepository {

    private final SessionFactory sessionFactory;

    public RoleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserRole> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from UserRole u";
            return session.createQuery(hqlQuery, UserRole.class).list();
        }
    }

    @Override
    public UserRole findById(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(UserRole.class, userId);
        }
    }

    @Override
    public UserRole save(UserRole userRole) {
        try (Session session = sessionFactory.openSession()) {
            session.save(userRole);
            return userRole;
        }
    }

    @Override
    public UserRole update(UserRole userId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(userId);
            transaction.commit();
            return userId;
        }
    }

    @Override
    public UserRole delete(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            UserRole userDeleteById = session.find(UserRole.class, userId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(userDeleteById);
            transaction.commit();
            return userDeleteById;
        }
    }
}
