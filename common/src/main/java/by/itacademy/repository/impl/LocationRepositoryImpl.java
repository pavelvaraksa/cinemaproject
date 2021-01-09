package by.itacademy.repository.impl;

import by.itacademy.repository.LocationRepository;
import by.itacademy.domain.Location;
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
public class LocationRepositoryImpl implements LocationRepository {

    private final SessionFactory sessionFactory;

    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Cacheable("locations")
    @Query(value = "select l from Location l")
    @Override
    public List<Location> findAll() {
        try (Session session = sessionFactory.openSession()) {
            String hqlQuery = "select u from Location u";
            return session.createQuery(hqlQuery, Location.class).list();
        }
    }

    @Override
    public Location findById(Long locationId) {
        try (Session session = sessionFactory.openSession()) {
            return session.find(Location.class, locationId);
        }
    }

    @Override
    public Location save(Location location) {
        try (Session session = sessionFactory.openSession()) {
            session.save(location);
            return location;
        }
    }

    @Override
    public Location update(Location locationId) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.update(locationId);
            transaction.commit();
            return locationId;
        }
    }

    @Override
    public Location delete(Long locationId) {
        try (Session session = sessionFactory.openSession()) {
            Location locationDeleteById = session.find(Location.class, locationId);
            Transaction transaction = session.getTransaction();
            transaction.begin();
            session.delete(locationDeleteById);
            transaction.commit();
            return locationDeleteById;
        }
    }
}
