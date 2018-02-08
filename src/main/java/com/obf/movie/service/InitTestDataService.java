package com.obf.movie.service;


import com.obf.movie.domain.Category;
import com.obf.movie.domain.Movie;
import com.obf.movie.domain.Person;
import com.obf.movie.domain.RoleType;
import com.obf.movie.repository.CategoryRepository;
import com.obf.movie.repository.MovieRepository;
import com.obf.movie.repository.PersonRepository;
import com.obf.movie.repository.RoleTypeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


@Service
public class InitTestDataService {
    private final MovieRepository movieRepository;
    private final CategoryRepository categoryRepository;
    private final RoleTypeRepository roleRepository;
    private final PersonRepository personRepository;


    public InitTestDataService(MovieRepository movieRepository, CategoryRepository categoryRepository, RoleTypeRepository roleRepository, PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.categoryRepository = categoryRepository;
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
    }



    @Transactional
    public void initData(Long numberOfData) {
        categoryRepository.save(getCategory("Action"));
        categoryRepository.save(getCategory("Drama"));

        roleRepository.save(getRoleType("Actor"));
        roleRepository.save(getRoleType("Director"));

        personRepository.save(getPerson("King","Kong"));
        personRepository.save(getPerson("Ola","Normand"));

        if (numberOfData > 2) {
            Movie mov = new Movie();
            mov.setCreated(Date.from(Instant.now()));
            mov.setModified(Date.from(Instant.now()));


            List<Category> categories = new ArrayList<>();
            categories.add(categoryRepository.findOneByOid(1L));
            mov.setCategories(categories);
            mov.setNorwegianTitle("Meg");
            mov.setOriginalTitle("Me");
            movieRepository.save(mov);
        }
        //return  mov;
    }

    private Category getCategory(String title) {
        Category category = new Category();
        category.setCreated(Date.from(Instant.now()));
        category.setModified(Date.from(Instant.now()));
        category.setModifiedBy("obf");
        category.setCreatedBy("obf");
        category.setTitle(title);
        return category;
    }

    private Person getPerson(String fName, String lName) {
        Person per = new Person();
        per.setCreated(Date.from(Instant.now()));
        per.setModified(Date.from(Instant.now()));
        per.setModifiedBy("obf");
        per.setCreatedBy("obf");
        per.setFirstName(fName);
        per.setLastName(lName);
        per.setBorn(Date.from(Instant.now()));
        per.setDeceased(Date.from(Instant.now()));
        return per;
    }

    private RoleType getRoleType(String title) {
        RoleType roleType = new RoleType();
        roleType.setCreated(Date.from(Instant.now()));
        roleType.setModified(Date.from(Instant.now()));
        roleType.setModifiedBy("obf");
        roleType.setCreatedBy("obf");
        roleType.setTitle(title);
        return roleType;
    }
}

