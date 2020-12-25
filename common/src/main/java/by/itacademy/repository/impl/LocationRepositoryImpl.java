package by.itacademy.repository.impl;

import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.LocationRepository;
import by.itacademy.domain.Location;
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
public class LocationRepositoryImpl implements LocationRepository {

    private final SessionFactory sessionFactory;

    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Location> findAll() throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            if (session != null) {
                String hqlQuery = "select u from Location u";
                return session.createQuery(hqlQuery, Location.class).list();
            } else {
                throw new RepositoryException("No one location does not exist");
            }
        }
    }

    @Override
    public Location findById(Long locationId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Location locationToFindById = session.find(Location.class, locationId);
            if (locationToFindById != null) {
                return locationToFindById;
            } else {
                throw new RepositoryException("Location does not exist");
            }
        }
    }

    @Override
    public Location save(Location location) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.save(location);
            if (location != null) {
                return location;
            } else {
                throw new RepositoryException("Location does not save");
            }
        }
    }

    @Override
    public Location update(Location locationId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(locationId);
            transaction.commit();
            if (locationId != null) {
                return locationId;
            } else {
                throw new RepositoryException("Location does not update");
            }
        }
    }

    @Override
    public Long delete(Location locationId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            session.delete(locationId);
            if (locationId != null) {
                return locationId.getId();
            } else {
                throw new RepositoryException("Location does not delete");
            }
        }
    }
}
