package com.obf.movie.service;

import com.obf.movie.Application;
import com.obf.movie.domain.Category;
import com.obf.movie.repository.CategoryRepository;
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

    private long categorySaved;

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
        Category cat = addCategory(0,"Action");


        Category getCat = categoryService.getOneCategory(cat.getOid());

        assertThat(getCat).as("Cound not find saved category").isNotNull();
        assertThat(getCat.getTitle()).as("Title is wrong").isEqualTo(cat.getTitle());


    }
    @Test
    public void saveNewCategory() throws Exception {
        Category cat = addCategory(0,"Action");
        assertThat(cat).as("Cound save category").isNotNull();
        assertThat(cat.getOid()).as("Cound save category, oid wrong").isEqualTo(categorySaved);

    }

    @Test
    public void updateCategory() throws Exception {

        Category cat = addCategory(0,"Action");
        Category getCat = categoryService.getOneCategory(cat.getOid());
        getCat.setTitle("Actioooon");

        Category catUpdated = categoryService.updateCategory(getCat);
        assertThat(catUpdated).as("Category not updated null back").isNotNull();
        assertThat(cat.getTitle()).as("Title is wrong").isEqualTo("Actioooon");

    }


    private Category addCategory(long oid, String title){
        categorySaved++;
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

}
