package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.MovieApp;

public interface MovieAppRepository extends CrudRepository<Long, MovieApp> {
}
