package by.itacademy.repository.impl;

import by.itacademy.domain.Location;
import by.itacademy.repository.LocationColumns;
import by.itacademy.repository.LocationRepository;
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

public class LocationRepositoryImpl implements LocationRepository {

    private static final Logger log = Logger.getLogger(LocationRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Location getLocationRowMapper(ResultSet rs, int i) throws SQLException {
        Location location = new Location();
        location.setId(rs.getLong(LocationColumns.ID));
        location.setLocation(rs.getString(LocationColumns.LOCATION));
        return location;
    }

    @Override
    public List<Location> findAll() {
        return jdbcTemplate.query("select * from m_location", this::getLocationRowMapper);
    }

    @Override
    public Location findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_location where id = ?", new Object[]{key}, this::getLocationRowMapper);
    }

    @Override
    public Location save(Location location) {
        final String createQuery = "insert into m_location (location) " +
                "values (:location);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("location", location.getLocation());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdLocationId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdLocationId);
    }

    @Override
    public Location update(Location location) {
        final String updateQuery = "update m_location set " +
                "location = ?, " +
                "where id = ?";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("location", location.getLocation());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(location.getId());
    }

    @Override
    public Long delete(Location location) {
        final String deleteQuery = "delete from m_location where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, location.getId());
    }
}
