package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.Movie;

public interface MovieHibernateRepository extends CrudRepository<Long, Movie> {
}
