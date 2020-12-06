package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.Ticket;

public interface TicketHibernateRepository extends CrudRepository<Long, Ticket> {
}
