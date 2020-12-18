package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.UserAppRepository;
import by.itacademy.domain.hibernate.UserApp;
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
public class UserAppRepositoryImpl implements UserAppRepository {

    private final SessionFactory sessionFactory;

    public UserAppRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserApp> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from UserApp u";
            return session.createQuery(hqlQuery, UserApp.class).list();
        }
    }

    @Override
    public UserApp findById(Long userHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(UserApp.class, userHibernateId);
        }
    }

    @Override
    public UserApp save(UserApp userApp) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(userApp);
            return userApp;
        }
    }

    @Override
    public UserApp update(UserApp userAppId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(userAppId);
            transaction.commit();
            return userAppId;
        }
    }

    @Override
    public Long delete(Long userHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(userHibernateId);
            transaction.commit();
            return userHibernateId;
        }
    }
}
