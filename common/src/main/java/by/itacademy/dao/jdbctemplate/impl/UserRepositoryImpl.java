package by.itacademy.dao.jdbctemplate.impl;

import by.itacademy.dao.jdbctemplate.UserRepository;
import by.itacademy.dao.jdbctemplate.columns.UserColumn;
import by.itacademy.domain.User;
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
public class UserRepositoryImpl implements UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private User getUserRowMapper(ResultSet rs, int i) throws SQLException {
        User user = new User();
        user.setId(rs.getLong(UserColumn.ID));
        user.setLogin(rs.getString(UserColumn.LOGIN));
        user.setPassword(rs.getString(UserColumn.PASSWORD));
        user.setCreated(rs.getTimestamp(UserColumn.CREATED));
        user.setChanged(rs.getTimestamp(UserColumn.CHANGED));
        return user;
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("select * from m_user", this::getUserRowMapper);
    }

    @Override
    public User findById(Long userId) {
        return jdbcTemplate.queryForObject("select * from m_user where id = ?", new Object[]{userId}, this::getUserRowMapper);
    }

    @Override
    public User save(User user) {
        final String createQuery = "insert into m_user (login, password, created, changed) " +
                "values (:login, :password, :created, :changed);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("created", user.getCreated());
        params.addValue("changed", user.getChanged());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdUserId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdUserId);
    }

    @Override
    public User update(User user) {
        final String updateQuery = "update m_user set " +
                "login = :login, " +
                "password = :password, " +
                "changed = :changed " +
                "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("login", user.getLogin());
        params.addValue("password", user.getPassword());
        params.addValue("changed", new Timestamp(System.currentTimeMillis()));
        params.addValue("id", user.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(user.getId());
    }

    @Override
    public Long delete(Long userId) {
        final String deleteQuery = "delete from m_user where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, userId);
    }
}
