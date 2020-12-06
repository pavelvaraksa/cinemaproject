package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.Event;

public interface EventHibernateRepository extends CrudRepository<Long, Event> {
}
