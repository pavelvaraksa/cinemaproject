package by.itacademy.repository.impl;

import by.itacademy.domain.Event;
import by.itacademy.repository.EventColumns;
import by.itacademy.repository.EventRepository;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Repository
public class EventRepositoryImpl implements EventRepository {

    private static final Logger log = Logger.getLogger(EventRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Event getEventRowMapper(ResultSet rs, int i) throws SQLException {
        Event event = new Event();
        event.setId(rs.getLong(EventColumns.ID));
        event.setDate(rs.getDate(EventColumns.DATE));
        event.setTime(rs.getTime(EventColumns.TIME));
        event.setMovieId(rs.getLong(EventColumns.MOVIE_ID));
        event.setCinemaId(rs.getLong(EventColumns.CINEMA_ID));
        return event;
    }

    @Override
    public List<Event> findAll() {
        return jdbcTemplate.query("select * from m_event", this::getEventRowMapper);
    }

    @Override
    public Event findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_event where id = ?", new Object[]{key}, this::getEventRowMapper);
    }

    @Override
    public Event save(Event event) {
        final String createQuery = "insert into m_event (date, time, movieId, cinemaId) " +
                "values (:date, :time, :movieId, :cinemaId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", event.getDate());
        params.addValue("time", event.getTime());
        params.addValue("movieId", event.getMovieId());
        params.addValue("cinemaId", event.getCinemaId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdEventId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdEventId);
    }

    @Override
    public Event update(Event event) {
        final String updateQuery = "update m_event set " +
                "date = ?, " +
                "time = ?, " +
                "movieId = ?, " +
                "cinemaId = ?, " +
                "where id = ?";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("date", event.getDate());
        params.addValue("time", event.getTime());
        params.addValue("movieId", event.getMovieId());
        params.addValue("cinemaId", event.getCinemaId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(event.getId());
    }

    @Override
    public Long delete(Event event) {
        final String deleteQuery = "delete from m_event where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, event.getId());
    }
}
