package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.UserHibernate;

public interface UserHibernateRepository extends CrudRepository<Long, UserHibernate> {
}
