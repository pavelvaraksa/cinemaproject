package by.itacademy.dao.jdbctemplate.impl;

import by.itacademy.dao.jdbctemplate.EventRepository;
import by.itacademy.dao.jdbctemplate.columns.EventColumn;
import by.itacademy.domain.Event;
import lombok.extern.log4j.Log4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Log4j
@Repository
public class EventRepositoryImpl implements EventRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public EventRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Event getEventRowMapper(ResultSet rs, int i) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong(EventColumn.ID));
        event.setDate(rs.getDate(EventColumn.DATE));
        event.setTime(rs.getTime(EventColumn.TIME));
        event.setCreated(rs.getTimestamp(EventColumn.CREATED));
        event.setChanged(rs.getTimestamp(EventColumn.CHANGED));
        event.setMovieId(rs.getLong(EventColumn.MOVIE_ID));
        event.setCinemaId(rs.getLong(EventColumn.CINEMA_ID));
        return event;
    }

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query("select * from m_event", this::getEventRowMapper);
    }

    @Override
    public Event findById(Long eventId) {
        return jdbcTemplate.queryForObject("select * from m_event where id = ?", new Object[]{eventId}, this::getEventRowMapper);
    }

    @Override
    public Event save(Event event) {
        final String createQuery = "insert into m_event (date, time, created, changed, movie_id, cinema_id) " +
                "values (:date, :time, :created, :changed, :movieId, :cinemaId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", event.getDate());
        params.addValue("time", event.getTime());
        params.addValue("created", event.getCreated());
        params.addValue("changed", event.getChanged());
        params.addValue("movieId", event.getMovieId());
        params.addValue("cinemaId", event.getCinemaId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdEventId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdEventId);
    }

    @Override
    public Event update(Event event) {
        final String updateQuery = "update m_event set " +
                "date = :date, " +
                "time = :time, " +
                "changed = :changed, " +
                "movie_id = :movieId, " +
                "cinema_id = :cinemaId " +
                "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", event.getDate());
        params.addValue("time", event.getTime());
        params.addValue("changed", new Timestamp(System.currentTimeMillis()));
        params.addValue("movieId", event.getMovieId());
        params.addValue("cinemaId", event.getCinemaId());
        params.addValue("id", event.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(event.getId());
    }

    @Override
    public Long delete(Long eventId) {
        final String deleteQuery = "delete from m_event where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, eventId);
    }
}
