package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.MovieHibernate;

public interface MovieHibernateRepository extends CrudRepository<Long, MovieHibernate> {
}
