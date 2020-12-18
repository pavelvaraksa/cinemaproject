package by.itacademy.dao.hibernate;

import by.itacademy.dao.jdbctemplate.CrudRepository;
import by.itacademy.domain.hibernate.TicketApp;

public interface TicketAppRepository extends CrudRepository<Long, TicketApp> {
}
