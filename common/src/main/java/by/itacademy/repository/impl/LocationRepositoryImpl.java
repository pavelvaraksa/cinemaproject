package by.itacademy.repository.impl;

import by.itacademy.domain.Location;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.LocationRepository;
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
            String locationsToFind = "select u from Location u";
            if (session != null) {
                return session.createQuery(locationsToFind, Location.class).list();
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
                throw new RepositoryException("Location with id " + locationId + " does not exist");
            }
        }
    }

    @Override
    public Location save(Location location) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Location locationToSave = session.find(Location.class, location);
            if (locationToSave != null) {
                return locationToSave;
            } else {
                throw new RepositoryException("Location " + location + " saved");
            }
        }
    }

    @Override
    public Location update(Location locationId) throws RepositoryException {
        try (Session session = sessionFactory.openSession()) {
            Location locationToUpdate = session.find(Location.class, locationId);
            if (locationToUpdate != null) {
                return locationToUpdate;
            } else {
                throw new RepositoryException("Location with id " + locationId + " updated");
            }
        }
    }

    @Override
    public Long delete(Long locationId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(locationId);
            transaction.commit();
            log.info("Location with id " + locationId + " deleted");
            return locationId;
        }
    }
}
