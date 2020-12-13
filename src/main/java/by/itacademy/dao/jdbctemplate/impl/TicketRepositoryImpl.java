package by.itacademy.dao.jdbctemplate.impl;

import by.itacademy.dao.jdbctemplate.TicketRepository;
import by.itacademy.dao.jdbctemplate.columns.TicketColumn;
import by.itacademy.domain.Ticket;
import lombok.extern.log4j.Log4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Log4j
@Repository
public class TicketRepositoryImpl implements TicketRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public TicketRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Ticket getTicketRowMapper(ResultSet rs, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(rs.getLong(TicketColumn.ID));
        ticket.setPlaceNumber(rs.getInt(TicketColumn.PLACE_NUMBER));
        ticket.setPrice(rs.getInt(TicketColumn.PRICE));
        ticket.setCreated(rs.getTimestamp(TicketColumn.CREATED));
        ticket.setChanged(rs.getTimestamp(TicketColumn.CHANGED));
        ticket.setUserId(rs.getLong(TicketColumn.USER_ID));
        ticket.setEventId(rs.getLong(TicketColumn.EVENT_ID));
        return ticket;
    }

    @Override
    public List<Ticket> findAll() {
        return jdbcTemplate.query("select * from m_ticket", this::getTicketRowMapper);
    }

    @Override
    public Ticket findById(Long ticketId) {
        return jdbcTemplate.queryForObject("select * from m_ticket where id = ?", new Object[]{ticketId}, this::getTicketRowMapper);
    }

    @Override
    public Ticket save(Ticket ticket) {
        final String createQuery = "insert into m_ticket (place_number, price, created, changed, user_id, event_id) " +
                "values (:placeNumber, :price, :created, :changed, :userId, :eventId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("placeNumber", ticket.getPlaceNumber());
        params.addValue("price", ticket.getPrice());
        params.addValue("created", ticket.getCreated());
        params.addValue("changed", ticket.getChanged());
        params.addValue("userId", ticket.getUserId());
        params.addValue("eventId", ticket.getEventId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdTicketId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdTicketId);
    }

    @Override
    public Ticket update(Ticket ticket) {
        final String updateQuery = "update m_ticket set " +
                "place_number = :placeNumber, " +
                "price = :price, " +
                "changed = :changed, " +
                "user_id = :userId, " +
                "event_id = :eventId " +
                "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("placeNumber", ticket.getPlaceNumber());
        params.addValue("price", ticket.getPrice());
        params.addValue("changed", new Timestamp(System.currentTimeMillis()));
        params.addValue("userId", ticket.getUserId());
        params.addValue("eventId", ticket.getEventId());
        params.addValue("id", ticket.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(ticket.getId());
    }

    @Override
    public Long delete(Long ticketId) {
        final String deleteQuery = "delete from m_ticket where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, ticketId);
    }
}
