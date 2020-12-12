package by.itacademy.dao.hibernate.impl;

import by.itacademy.dao.hibernate.UserHibernateRepository;
import by.itacademy.domain.hibernate.UserHibernate;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
@Log4j2
public class UserHibernateRepositoryImpl implements UserHibernateRepository {

    private final SessionFactory sessionFactory;

    public UserHibernateRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<UserHibernate> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from UserHibernate u";
            return session.createQuery(hqlQuery, UserHibernate.class).list();
        }
    }

    @Override
    public UserHibernate findById(Long userHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(UserHibernate.class, userHibernateId);
        }
    }

    @Override
    public UserHibernate save(UserHibernate userHibernate) {
        try (Session session = sessionFactory.openSession()) {
            session.saveOrUpdate(userHibernate);
            return userHibernate;
        }
    }

    @Override
    public UserHibernate update(UserHibernate userHibernateId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.saveOrUpdate(userHibernateId);
            transaction.commit();
            return userHibernateId;
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
