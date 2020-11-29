package by.itacademy.repository.impl;

import by.itacademy.domain.Movie;
import by.itacademy.repository.MovieColumns;
import by.itacademy.repository.MovieRepository;
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
public class MovieRepositoryImpl implements MovieRepository {

    private static final Logger log = Logger.getLogger(MovieRepositoryImpl.class);

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private Movie getMovieRowMapper(ResultSet rs, int i) throws SQLException {
        Movie movie = new Movie();
        movie.setId(rs.getLong(MovieColumns.ID));
        movie.setTitle(rs.getString(MovieColumns.TITLE));
        movie.setGenre(rs.getString(MovieColumns.GENRE));
        movie.setYear(rs.getInt(MovieColumns.YEAR));
        movie.setDuration(rs.getInt(MovieColumns.DURATION));
        return movie;
    }

    @Override
    public List<Movie> findAll() {
        return jdbcTemplate.query("select * from m_movie", this::getMovieRowMapper);
    }

    @Override
    public Movie findById(Long key) {
        return jdbcTemplate.queryForObject("select * from m_movie where id = ?", new Object[]{key}, this::getMovieRowMapper);
    }

    @Override
    public Movie save(Movie movie) {
        final String createQuery = "insert into m_movie (title, genre, year, duration) " +
                "values (:title, :genre, :year, :duration);";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", movie.getTitle());
        params.addValue("genre", movie.getGenre());
        params.addValue("year", movie.getYear());
        params.addValue("duration", movie.getDuration());

        namedParameterJdbcTemplate.update(createQuery, params, keyHolder, new String[]{"id"});

        long createdMovieId = Objects.requireNonNull(keyHolder.getKey()).longValue();

        return findById(createdMovieId);
    }

    @Override
    public Movie update(Movie movie) {
        final String updateQuery = "update m_movie set " +
                "title = ?, " +
                "genre = ?, " +
                "year = ?, " +
                "duration = ?, " +
                "where id = ?";

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", movie.getTitle());
        params.addValue("genre", movie.getGenre());
        params.addValue("year", movie.getYear());
        params.addValue("duration", movie.getDuration());

        namedParameterJdbcTemplate.update(updateQuery, params);

        return findById(movie.getId());
    }

    @Override
    public Long delete(Movie movie) {
        final String deleteQuery = "delete from m_movie where id = ?";

        return (long) jdbcTemplate.update(deleteQuery, movie.getId());
    }
}
