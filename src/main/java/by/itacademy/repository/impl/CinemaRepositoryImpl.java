package by.itacademy.repository.impl;

import by.itacademy.domain.Cinema;
import by.itacademy.repository.CinemaColumns;
import by.itacademy.repository.CinemaRepository;
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

public class CinemaRepositoryImpl implements CinemaRepository {

    private static final Logger log = Logger.getLogger(CinemaRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Cinema getCinemaRowMapper(ResultSet rs, int i) throws SQLException {
        Cinema cinema = new Cinema();
        cinema.setId(rs.getLong(CinemaColumns.ID));
        cinema.setName(rs.getString(CinemaColumns.NAME));
        cinema.setTicketsCount(rs.getInt(CinemaColumns.TICKETS_COUNT));
        cinema.setPhoneNumber(rs.getInt(CinemaColumns.PHONE_NUMBER));
        cinema.setPaymentMethod(rs.getString(CinemaColumns.PAYMENT_METHOD));
        cinema.setLocationId(rs.getLong(CinemaColumns.LOCATION_ID));
        cinema.setMovieId(rs.getLong(CinemaColumns.MOVIE_ID));
        return cinema;
    }

    @Override
    public List<Cinema> findAll() {
        return jdbcTemplate.query("select * from m_cinema", this::getCinemaRowMapper);
    }

    @Override
    public Cinema findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_cinema where id = ?", new Object[]{key}, this::getCinemaRowMapper);
    }

    @Override
    public Cinema save(Cinema cinema) {
        final String createQuery = "insert into m_cinema (name, ticketsCount, phoneNumber, paymentMethod, locationId, movieId) " +
                "values (:name, :ticketsCount, :phoneNumber, :paymentMethod, :locationId, :movieId);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", cinema.getName());
        params.addValue("ticketsCount", cinema.getTicketsCount());
        params.addValue("phoneNumber", cinema.getPhoneNumber());
        params.addValue("paymentMethod", cinema.getPaymentMethod());
        params.addValue("locationId", cinema.getLocationId());
        params.addValue("movieId", cinema.getMovieId());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdCinemaId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdCinemaId);
    }

    @Override
    public Cinema update(Cinema cinema) {
        final String updateQuery = "update m_cinema set " +
                "name = ?, " +
                "ticketsCount = ?, " +
                "phoneNumber = ?, " +
                "paymentMethod = ?, " +
                "locationId = ?, " +
                "movieId = ?, " +
                "where id = ?";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", cinema.getName());
        params.addValue("ticketsCount", cinema.getTicketsCount());
        params.addValue("phoneNumber", cinema.getPhoneNumber());
        params.addValue("paymentMethod", cinema.getPaymentMethod());
        params.addValue("locationId", cinema.getLocationId());
        params.addValue("movieId", cinema.getMovieId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(cinema.getId());
    }

    @Override
    public Long delete(Cinema cinema) {
        final String deleteQuery = "delete from m_cinema where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, cinema.getId());
    }
}
