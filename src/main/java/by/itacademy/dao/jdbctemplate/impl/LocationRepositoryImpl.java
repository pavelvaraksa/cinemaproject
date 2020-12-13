package by.itacademy.dao.jdbctemplate.impl;

import by.itacademy.dao.jdbctemplate.LocationRepository;
import by.itacademy.dao.jdbctemplate.columns.LocationColumn;
import by.itacademy.domain.Location;
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
public class LocationRepositoryImpl implements LocationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public LocationRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Location getLocationRowMapper(ResultSet rs, int i) throws SQLException {
        Location location = new Location();
        location.setId(rs.getLong(LocationColumn.ID));
        location.setLocation(rs.getString(LocationColumn.LOCATION));
        location.setCreated(rs.getTimestamp(LocationColumn.CREATED));
        location.setChanged(rs.getTimestamp(LocationColumn.CHANGED));
        return location;
    }

    @Override
    public List<Location> findAll() {
        return jdbcTemplate.query("select * from m_location", this::getLocationRowMapper);
    }

    @Override
    public Location findById(Long locationId) {
        return jdbcTemplate.queryForObject("select * from m_location where id = ?", new Object[]{locationId}, this::getLocationRowMapper);
    }

    @Override
    public Location save(Location location) {
        final String createQuery = "insert into m_location (location, created, changed) " +
                "values (:location, :created, :changed);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("location", location.getLocation());
        params.addValue("created", location.getCreated());
        params.addValue("changed", location.getChanged());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdLocationId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdLocationId);
    }

    @Override
    public Location update(Location location) {
        final String updateQuery = "update m_location set " +
                "location = :location, " +
                "changed = :changed " +
                "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("location", location.getLocation());
        params.addValue("changed", new Timestamp(System.currentTimeMillis()));
        params.addValue("id", location.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(location.getId());
    }

    @Override
    public Long delete(Long locationId) {
        final String deleteQuery = "delete from m_location where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, locationId);
    }
}
