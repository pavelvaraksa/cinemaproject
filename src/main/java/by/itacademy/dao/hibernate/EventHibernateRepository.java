package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.EventHibernate;

public interface EventHibernateRepository extends CrudRepository<Long, EventHibernate> {
}
