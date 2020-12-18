package by.itacademy.dao.jdbctemplate.impl;

import by.itacademy.dao.jdbctemplate.MovieRepository;
import by.itacademy.dao.jdbctemplate.columns.MovieColumn;
import by.itacademy.domain.Movie;
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
public class MovieRepositoryImpl implements MovieRepository {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public MovieRepositoryImpl(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    private Movie getMovieRowMapper(ResultSet rs, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getLong(MovieColumn.ID));
        movie.setTitle(rs.getString(MovieColumn.TITLE));
        movie.setGenre(rs.getString(MovieColumn.GENRE));
        movie.setYear(rs.getInt(MovieColumn.YEAR));
        movie.setDuration(rs.getInt(MovieColumn.DURATION));
        movie.setCreated(rs.getTimestamp(MovieColumn.CREATED));
        movie.setChanged(rs.getTimestamp(MovieColumn.CHANGED));
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query("select * from m_movie", this::getMovieRowMapper);
    }

    @Override
    public Movie findById(Long movieId) {
        return jdbcTemplate.queryForObject("select * from m_movie where id = ?", new Object[]{movieId}, this::getMovieRowMapper);
    }

    @Override
    public Movie save(Movie movie) {
        final String createQuery = "insert into m_movie (title, genre, year, duration, created, changed) " +
                "values (:title, :genre, :year, :duration, :created, :changed);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", movie.getTitle());
        params.addValue("genre", movie.getGenre());
        params.addValue("year", movie.getYear());
        params.addValue("duration", movie.getDuration());
        params.addValue("created", movie.getCreated());
        params.addValue("changed", movie.getChanged());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdMovieId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdMovieId);
    }

    @Override
    public Movie update(Movie movie) {
        final String updateQuery = "update m_movie set " +
                "title = :title, " +
                "genre = :genre, " +
                "year = :year, " +
                "duration = :duration, " +
                "created = :created, " +
                "changed = :changed " +
                "where id = :id";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", movie.getTitle());
        params.addValue("genre", movie.getGenre());
        params.addValue("year", movie.getYear());
        params.addValue("duration", movie.getDuration());
        params.addValue("changed", new Timestamp(System.currentTimeMillis()));
        params.addValue("id", movie.getId());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(movie.getId());
    }

    @Override
    public Long delete(Long movieId) {
        final String deleteQuery = "delete from m_movie where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, movieId);
    }
}
