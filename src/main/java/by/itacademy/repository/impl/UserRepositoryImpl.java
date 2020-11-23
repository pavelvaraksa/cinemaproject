package by.itacademy.repository.impl;

import by.itacademy.domain.User;
import by.itacademy.repository.UserColumns;
import by.itacademy.repository.UserRepository;
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

public class UserRepositoryImpl implements UserRepository {

    private static final Logger log = Logger.getLogger(UserRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private User getUserRowMapper(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(UserColumns.ID));
        user.setLogin(rs.getString(UserColumns.LOGIN));
        user.setPassword(rs.getString(UserColumns.PASSWORD));
        user.setRole(rs.getString(UserColumns.ROLE));
        user.setCreated(rs.getTimestamp(UserColumns.CREATED));
        user.setChanged(rs.getTimestamp(UserColumns.CHANGED));
        return user;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from m_user", this::getUserRowMapper);
    }

    @Override
    public User findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_user where id = ?", new Object[]{key}, this::getUserRowMapper);
    }

    @Override
    public User save(User user) {
        final String createQuery = "insert into m_user (login, password, role, created, changed) " +
                "values (:login, :password, :role, :created, :changed);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("role", user.getRole());
        params.addValue("created", user.getCreated());
        params.addValue("changed", user.getChanged());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_user set " +
                "login = ?, " +
                "password = ?, " +
                "role = ?, " +
                "created = ?, " +
                "changed = ?, " +
                "where id = ?";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("role", user.getRole());
        params.addValue("created", user.getCreated());
        params.addValue("changed", user.getChanged());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(user.getId());
    }

    @Override
    public Long delete(User user) {
        final String deleteQuery = "delete from m_user where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, user.getId());
    }
}
