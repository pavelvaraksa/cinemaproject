package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.Location;

public interface LocationHibernateRepository extends CrudRepository<Long, Location> {
}
