package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.User;

public interface UserHibernateRepository extends CrudRepository<Long, User> {
}
