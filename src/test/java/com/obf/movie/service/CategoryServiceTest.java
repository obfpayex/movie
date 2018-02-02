package com.obf.movie.service;

import com.obf.movie.Application;
import com.obf.movie.domain.Category;
import com.obf.movie.repository.CategoryRepository;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import java.sql.Date;
import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;


@ActiveProfiles("unit-test")
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
public class CategoryServiceTest {

    private CategoryService categoryService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        //MockitoAnnotations.initMocks(this);
        categoryService = new CategoryService(categoryRepository);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getOneCategory() throws Exception {



    }
    @Test
    public void saveNewCategory() throws Exception {
        //Category cat = categoryService.saveNewCategory(makeCategory(0,"Action"));
        Category cat = addCategory(0,"Action");


            Category getCat = categoryService.getOneCategory(cat.getOid());

        assertThat(getCat).as("Cound not find saved category").isNotNull();
        assertThat(getCat.getTitle()).as("Title is wrong").isEqualTo(cat.getTitle());
    }

    @Test
    public void updateCategory() throws Exception {
    }


    private Category addCategory(long oid, String title){
        Category cat =  new Category();
        cat.setCreated(Date.from(Instant.now()));
        cat.setModified(Date.from(Instant.now()));
        cat.setTitle(title);

        if (oid > 0){
            cat.setOid(oid);
        }

        categoryRepository.save(cat);
        return cat;
    }

}
