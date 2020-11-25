package by.itacademy.repository.impl;

import by.itacademy.domain.Ticket;
import by.itacademy.repository.TicketColumns;
import by.itacademy.repository.TicketRepository;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class TicketRepositoryImpl implements TicketRepository {

    private static final Logger log = Logger.getLogger(TicketRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Ticket getTicketRowMapper(ResultSet rs, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong(TicketColumns.ID));
        ticket.setPlaceNumber(rs.getInt(TicketColumns.PLACE_NUMBER));
        ticket.setPrice(rs.getInt(TicketColumns.PRICE));
        ticket.setUserId(rs.getLong(TicketColumns.USER_ID));
        ticket.setEventId(rs.getLong(TicketColumns.EVENT_ID));
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        return jdbcTemplate.query("select * from m_ticket", this::getTicketRowMapper);
    }

    @Override
    public Ticket findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_ticket where id = ?", new Object[]{key}, this::getTicketRowMapper);
    }

    @Override
    public Ticket save(Ticket ticket) {
        final String createQuery = "insert into m_ticket (placeNumber, price, userId, eventId) " +
                "values (:placeNumber, :price, :userId, :eventId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("placeNumber", ticket.getPlaceNumber());
        params.addValue("price", ticket.getPrice());
        params.addValue("userId", ticket.getUserId());
        params.addValue("eventId", ticket.getEventId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdTicketId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdTicketId);
    }

    @Override
    public Ticket update(Ticket ticket) {
        final String updateQuery = "update m_ticket set " +
                "placeNumber = ?, " +
                "price = ?, " +
                "userId = ?, " +
                "eventId = ?, " +
                "where id = ?";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("placeNumber", ticket.getPlaceNumber());
        params.addValue("price", ticket.getPrice());
        params.addValue("userId", ticket.getUserId());
        params.addValue("eventId", ticket.getEventId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(ticket.getId());
    }

    @Override
    public Long delete(Ticket ticket) {
        final String deleteQuery = "delete from m_ticket where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, ticket.getId());
    }
}
