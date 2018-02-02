package com.obf.movie.service;

import com.obf.movie.domain.Category;
import com.obf.movie.domain.Movie;
import com.obf.movie.domain.Transaction;
import com.obf.movie.repository.CategoryRepository;
import com.obf.movie.repository.MovieRepository;
import com.obf.movie.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    public MovieService(MovieRepository movieRepository, CategoryRepository categoryRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
    }


    @Transactional(readOnly = true)
    public Movie getOneMovie(Long oid) {

        Movie tx = movieRepository.findOneByOid(oid);
        if (tx == null)
            log.info("Could not find Movie by oid: {}", oid);

        return tx;
    }

    @Transactional
    public Movie saveNewMovie(Movie movie) {
        log.info("Saving movie");

        Set<Category> categories = movie.getCategories().stream().collect(Collectors.toSet());
        movie.getCategories().clear();
        for (Category cat : categories){
            Category item = categoryRepository.findOneByOid(cat.getOid());
            if (item!= null){
                movie.getCategories().add(item);
            } else {
                movie.getCategories().add(cat);
            }
        }
        movie.setModified(Date.from(Instant.now()));
        movie.setCreated(Date.from(Instant.now()));
        movieRepository.save(movie);
        return movie;
    }

    @Transactional
    public Movie updateMovie(Movie movie) {

        Movie mov = movieRepository.findOneByOid(movie.getOid());
        if (mov == null) {
            log.info("Could not find Movie by oid: {}", movie.getOid());
            return null;
        }

        log.info("Updating Movie with oid: {}", mov.getOid());
        movie.setModified(Date.from(Instant.now()));
        movie = movieRepository.save(movie);

        return movie;
    }

}