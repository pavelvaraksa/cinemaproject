package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.Cinema;

public interface CinemaHibernateRepository extends CrudRepository<Long, Cinema> {
}
