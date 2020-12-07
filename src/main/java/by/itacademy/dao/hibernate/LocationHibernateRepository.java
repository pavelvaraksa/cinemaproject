package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.LocationHibernate;

public interface LocationHibernateRepository extends CrudRepository<Long, LocationHibernate> {
}
