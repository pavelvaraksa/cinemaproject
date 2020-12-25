package by.itacademy.service.impl;

import by.itacademy.domain.Cinema;
import by.itacademy.exception.RepositoryException;
import by.itacademy.repository.CinemaRepository;
import by.itacademy.service.CinemaService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class CinemaServiceImpl implements CinemaService {

    private final CinemaRepository cinemaRepository;

    public CinemaServiceImpl(CinemaRepository cinemaRepository) {
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public List<Cinema> findAll()  {
        try {
            List<Cinema> cinemasToFind = cinemaRepository.findAll();
            log.info("Cinemas " + cinemasToFind + " exist");
            return cinemasToFind;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Cinema findById(Long cinemaId) {
        try {
            Cinema cinemaToFindById = cinemaRepository.findById(cinemaId);
            log.info("Cinema with id " + cinemaId + " exists");
            return cinemaToFindById;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Cinema save(Cinema cinema) {
        try {
            Cinema cinemaToSave = cinemaRepository.save(cinema);
            log.info("Cinema " + cinema + " was saved");
            return cinemaToSave;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Cinema update(Cinema cinema) {
        try {
            Cinema cinemaToUpdate = cinemaRepository.update(cinema);
            log.info("Cinema " + cinema + " was updated");
            return cinemaToUpdate;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Long delete(Cinema cinemaId) {
        try {
            Long cinemaToDelete = cinemaRepository.delete(cinemaId);
            log.info("Cinema with id " + cinemaId + " was deleted");
            return cinemaToDelete;
        } catch (RepositoryException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}