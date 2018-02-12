package com.obf.movie.service;

import com.obf.movie.domain.*;
import com.obf.movie.repository.*;
import com.obf.movie.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final CategoryService categoryService;
    private final RoleService roleService;
    private final LanguageService languageService;
    private final CountryService countryService;


    private static final Logger log = LoggerFactory.getLogger(MovieService.class);

    public MovieService(MovieRepository movieRepository, CategoryService categoryService, RoleService roleService, LanguageService languageService, CountryService countryService) {
        this.movieRepository = movieRepository;
        this.categoryService = categoryService;
        this.roleService = roleService;
        this.languageService = languageService;
        this.countryService = countryService;
    }

    @Transactional(readOnly = true)
    public Movie getOneMovie(Long oid) {

        Movie mov = movieRepository.findOneByOid(oid);
        if (mov == null)
            log.info("Could not find Movie by oid: {}", oid);

        return mov;
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {

        List<Movie> movies = movieRepository.findAll();
        if (movies == null)
            log.info("Could not find any Movies");

        return movies;
    }


    @Transactional(readOnly = true)
    public List<Movie> getMoviesByOriginalTitle(String orgTitle) {

        List<Movie> movies = movieRepository.findByOriginalTitle(orgTitle);
        if (movies == null)
            log.info("Could not find any Movies");

        return movies;
    }


    @Transactional(readOnly = true)
    public List<Movie> searchMoviesByOriginalTitle(String orgTitle) {

        List<Movie> movies = movieRepository.findByOriginalTitleLike(orgTitle);
        if (movies == null)
            log.info("Could not find any Movies");

        return movies;
    }

    @Transactional
    public Movie saveNewMovie(Movie movie) {
        log.info("Saving movie");

        getCategoryFromDB(movie);
        setRoleWithDdataFromDB(movie);
        getLanguageFromDB(movie);
        getCountryFromDB(movie);
        movieRepository.save(movie);

        return movie;
    }

    private void setRoleWithDdataFromDB(Movie movie) {
        List<Role> roles = roleService.setRoleWithDataFromDB(new ArrayList<>(movie.getRoles()));
        movie.getRoles().clear();
        movie.setRoles(roles);
    }

    private void getCategoryFromDB(Movie movie) {
        List<Category> categories = categoryService.getCategoriesFromDB(new ArrayList<>(movie.getCategories()));
        movie.getCategories().clear();
        movie.setCategories(categories);
    }

    private void getLanguageFromDB(Movie movie) {
        List<Language> languages = languageService.getLanguagesFromDB(new ArrayList<>(movie.getLanguages()));
        movie.getLanguages().clear();
        movie.setLanguages(languages);
    }

    private void getCountryFromDB(Movie movie) {
        List<Country> countries = countryService.getCountriesFromDB(new ArrayList<>(movie.getOriginCountry()));
        movie.getOriginCountry().clear();
        movie.setOriginCountry(countries);
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

        return  movieRepository.save(movie);
    }

    @Transactional
    public Movie partialUpdate(Movie transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Movie mov = movieRepository.findOneByOid(transaction.getOid());
        if (mov == null) {
            log.info("Could not find Transaction by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating Transaction with oid: {}", mov.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, mov);
        mov.setModified(Date.from(Instant.now()));
        mov = movieRepository.save(mov);

        return mov;
    }


    @Transactional
    public boolean deleteMovie(Long oid) throws Exception {

        Movie mov = movieRepository.findOneByOid(oid);
        if (mov == null) {
            log.info("Could not find Movie by oid: {}", oid);
            return false;
        }
        movieRepository.delete(mov);
        return true;
    }
}
