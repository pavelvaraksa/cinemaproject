package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.TicketHibernate;

public interface TicketHibernateRepository extends CrudRepository<Long, TicketHibernate> {
}
