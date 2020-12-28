package by.itacademy.service.impl;

import by.itacademy.domain.Cinema;
import by.itacademy.exception.RepositoryException;
import by.itacademy.exception.ServiceException;
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
    public List<Cinema> findAll() throws ServiceException {
        List<Cinema> existingCinemas;
        try {
            existingCinemas = cinemaRepository.findAll();
            if (existingCinemas.isEmpty()) {
                String errorMessage = "The list is empty.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            } else {
                log.info("Cinemas exist.");
                return existingCinemas;
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Cinema service exception while trying to find all cinemas." + e.getMessage());
        }
    }

    @Override
    public Cinema findById(Long cinemaId) throws ServiceException {
        Cinema cinemaToFindById;

        try {
            cinemaToFindById = cinemaRepository.findById(cinemaId);
            if (cinemaToFindById == null) {
                String errorMessage = "Cinema id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Cinema service exception while trying to find a cinema." + e.getMessage());
        }

        try {
            log.info("Cinema with id " + cinemaId + " exists.");
            return cinemaRepository.findById(cinemaId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a cinema.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Cinema save(Cinema cinema) throws ServiceException {
        List<Cinema> existingCinemas;
        try {
            existingCinemas = cinemaRepository.findAll();
        } catch (RepositoryException e) {
            String errorMessage = "Can't get all cinemas.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }

        for (Cinema existingCinema : existingCinemas) {
            boolean hasSameCinema = existingCinema.getName().equals(cinema.getName());

            if (hasSameCinema) {
                String errorMessage = "Cinema with name " + cinema.getName() + " already exists.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        }

        try {
            Cinema savedCinema = cinemaRepository.save(cinema);
            log.info("Cinema " + cinema + " was saved");
            return savedCinema;
        } catch (RepositoryException e) {
            throw new ServiceException("Cinema service exception while trying to save a cinema:" + e.getMessage());
        }
    }

    @Override
    public Cinema update(Cinema cinema) throws ServiceException {
        try {
            return cinemaRepository.update(cinema);
        } catch (RepositoryException e) {
            String errorMessage = "Can't get a cinema.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }

    @Override
    public Cinema delete(Long cinemaId) throws ServiceException {
        Cinema cinemaToFindById;

        try {
            cinemaToFindById = cinemaRepository.findById(cinemaId);
            if (cinemaToFindById == null) {
                String errorMessage = "Cinema id can't be null.";
                log.error(errorMessage);
                throw new ServiceException(errorMessage);
            }
        } catch (RepositoryException e) {
            throw new ServiceException("Cinema service exception while trying to delete a cinema." + e.getMessage());
        }

        try {
            log.info("Cinema with id " + cinemaId + " was deleted.");
            return cinemaRepository.delete(cinemaId);
        } catch (RepositoryException e) {
            String errorMessage = "Can't find a cinema.";
            log.error(errorMessage);
            throw new ServiceException(errorMessage);
        }
    }
}