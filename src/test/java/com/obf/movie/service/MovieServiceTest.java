package com.obf.movie.service;

import com.obf.movie.Application;
import com.obf.movie.domain.*;
import com.obf.movie.repository.CategoryRepository;
import com.obf.movie.repository.MovieRepository;
import com.obf.movie.repository.PersonRepository;
import com.obf.movie.repository.RoleTypeRepository;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@ActiveProfiles("unit-test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class MovieServiceTest {


    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;
    private PersonService personService;
    @Autowired
    private PersonRepository personRepository;
    private RoleTypeService roleTypeService;
    @Autowired
    private RoleTypeRepository roleTypeRepository;
    private MovieService movieService;
    @Autowired
    MovieRepository movieRepository;

    List<Category> categories;
    List<Person> people;
    List<Role> roles;

    @Ignore
    @Before
    public void setUp() throws Exception {
        categoryService = new CategoryService(categoryRepository);
        categories = new ArrayList<>();
        categories.add(addCategory(0,"Action"));
        categories.add(addCategory(0,"Drama"));

        personService = new PersonService(personRepository);
        roleTypeService = new RoleTypeService(roleTypeRepository);
        roles = new ArrayList<>();
        roles.add(new Role());
        roles.get(0).setPerson(addPerson(0,"Ola","Normand"));
        roles.get(0).setRoleType(addRoleType(0,"Actor"));

        roles.add(new Role());
        roles.get(0).setPerson(addPerson(0,"King","Kong"));
        roles.get(0).setRoleType(addRoleType(0,"Director"));

        //movieService = new MovieService(movieRepository,personRepository,roleTypeRepository,categoryService);

    }
    @Ignore
    @Test
    public void saveNewMovie() throws Exception {
        Movie mov = addMovie(0,"min mokk","my mock");

        assertThat(mov).as("Cound save category").isNotNull();
        assertThat(mov.getOid()).as("Cound save category, oid wrong").isEqualTo(0);

    }



    private Category addCategory(long oid, String title){
        return categoryRepository.save(makeCategory(oid, title));
    }

    private Category makeCategory(long oid, String title) {
        Category cat =  new Category();
        cat.setCreated(Date.from(Instant.now()));
        cat.setModified(Date.from(Instant.now()));
        cat.setTitle(title);

        if (oid > 0){
            cat.setOid(oid);
        }
        return cat;
    }

    private Person addPerson(long oid, String fName, String lName){
        return personRepository.save(makePerson(oid, fName, lName));
    }

    private Person makePerson(long oid, String fName, String lName){
        Person person = new Person();
        person.setFirstName(fName);
        person.setLastName(lName);
        person.setCreated(Date.from(Instant.now()));
        person.setModified(Date.from(Instant.now()));
        person.setBorn(Date.from(Instant.now()));

        if (oid > 0){
            person.setOid(oid);
        }


        return person;
    }


    private RoleType addRoleType(long oid, String title){
        return roleTypeRepository.save(makeRoleType(oid, title));
    }

    private RoleType makeRoleType(long oid, String title){
        RoleType rt = new RoleType();
        rt.setCreated(Date.from(Instant.now()));
        rt.setModified(Date.from(Instant.now()));
        rt.setTitle(title);

        if (oid > 0){
            rt.setOid(oid);
        }


        return rt;
    }

    private Movie addMovie(long oid, String nTitle, String oTitle){
        return movieService.saveNewMovie(makeMovie(oid, nTitle, oTitle));
    }
    private Movie makeMovie(long oid, String nTitle, String oTitle){
        Movie movie = new Movie();
        movie.setCreated(Date.from(Instant.now()));
        movie.setModified(Date.from(Instant.now()));
        movie.setCategories(new ArrayList<Category>());
        movie.getCategories().add(new Category());
        movie.getCategories().get(0).setOid(1l);
        movie.setRoles(new ArrayList<Role>());

        movie.getRoles().add(new Role());
        movie.getRoles().get(0).setPerson(new Person());
        movie.getRoles().get(0).getPerson().setOid(1l);
        movie.getRoles().get(0).setRoleType(new RoleType());
        movie.getRoles().get(0).getRoleType().setOid(1l);

        movie.setOriginalTitle(oTitle);
        movie.setNorwegianTitle(nTitle);

        if (oid > 0){
            movie.setOid(oid);
        }


        return movie;
    }

}
