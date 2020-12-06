package by.itacademy.dao.jdbctemplate.impl;

import by.itacademy.dao.jdbctemplate.CinemaRepository;
import by.itacademy.dao.jdbctemplate.columns.CinemaColumn;
import by.itacademy.domain.Cinema;
import lombok.extern.log4j.Log4j2;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Primary;
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

@Repository
public class CinemaRepositoryImpl implements CinemaRepository {

    private static final Logger log = Logger.getLogger(CinemaRepositoryImpl.class);

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CinemaRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Cinema getCinemaRowMapper(ResultSet rs, int i) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setId(rs.getLong(CinemaColumn.ID));
        cinema.setName(rs.getString(CinemaColumn.NAME));
        cinema.setTicketsCount(rs.getInt(CinemaColumn.TICKETS_COUNT));
        cinema.setPhoneNumber(rs.getString(CinemaColumn.PHONE_NUMBER));
        cinema.setPaymentMethod(rs.getString(CinemaColumn.PAYMENT_METHOD));
        cinema.setCreated(rs.getTimestamp(CinemaColumn.CREATED));
        cinema.setChanged(rs.getTimestamp(CinemaColumn.CHANGED));
        cinema.setLocationId(rs.getLong(CinemaColumn.LOCATION_ID));
        cinema.setMovieId(rs.getLong(CinemaColumn.MOVIE_ID));
        return cinema;
    }

    @Override
    public List<Cinema> findAll() {
        return jdbcTemplate.query("select * from m_cinema", this::getCinemaRowMapper);
    }

    @Override
    public Cinema findById(Long cinemaId) {
        return jdbcTemplate.queryForObject("select * from m_cinema where id = ?", new Object[]{cinemaId}, this::getCinemaRowMapper);
    }

    @Override
    public Cinema save(Cinema cinema) {
        final String createQuery = "insert into m_cinema (name, ticketsCount, phoneNumber, paymentMethod, created, changed, locationId, movieId) " +
                "values (:name, :ticketsCount, :phoneNumber, :paymentMethod, :created, :changed, :locationId, :movieId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", cinema.getName());
        params.addValue("ticketsCount", cinema.getTicketsCount());
        params.addValue("phoneNumber", cinema.getPhoneNumber());
        params.addValue("paymentMethod", cinema.getPaymentMethod());
        params.addValue("created", cinema.getCreated());
        params.addValue("changed", cinema.getChanged());
        params.addValue("locationId", cinema.getLocationId());
        params.addValue("movieId", cinema.getMovieId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdCinemaId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdCinemaId);
    }

    @Override
    public Cinema update(Cinema cinema) {
        final String updateQuery = "update m_cinema set " +
                "name = :name, " +
                "ticketsCount = :ticketsCount, " +
                "phoneNumber = :phoneNumber, " +
                "paymentMethod = :paymentMethod, " +
                "changed = :changed, " +
                "locationId = :locationId, " +
                "movieId = :movieId, " +
                "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", cinema.getName());
        params.addValue("ticketsCount", cinema.getTicketsCount());
        params.addValue("phoneNumber", cinema.getPhoneNumber());
        params.addValue("paymentMethod", cinema.getPaymentMethod());
        params.addValue("changed", new Timestamp(System.currentTimeMillis()));
        params.addValue("locationId", cinema.getLocationId());
        params.addValue("movieId", cinema.getMovieId());
        params.addValue("id", cinema.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(cinema.getId());
    }

    @Override
    public Long delete(Long cinemaId) {
        final String deleteQuery = "delete from m_cinema where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, cinemaId);
    }
}
