package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.UserApp;

public interface UserAppRepository extends CrudRepository<Long, UserApp> {
}
