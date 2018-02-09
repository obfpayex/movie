package com.obf.movie.service;


import com.obf.movie.domain.Category;
import com.obf.movie.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Category getOneCategory(Long oid) {

        Category cat = categoryRepository.findOneByOid(oid);
        if (cat == null)
            log.info("Could not find Category by oid: {}", oid);

        return cat;
    }

    @Transactional
    public Category saveNewCategory(Category category) {
        log.info("Saving Category");
        category.setModified(Date.from(Instant.now()));
        category.setCreated(Date.from(Instant.now()));
        categoryRepository.save(category);
        return category;
    }

    @Transactional
    public Category updateCategory(Category category) {

        Category cat = categoryRepository.findOneByOid(category.getOid());
        if (cat == null) {
            log.info("Could not find Category by oid: {}", category.getOid());
            return null;
        }

        log.info("Updating Category with oid: {}", cat.getOid());
        category.setModified(Date.from(Instant.now()));

        return categoryRepository.save(category);
    }


    @Transactional
    public Category partialUpdate(Category transaction) throws Exception {
        if (transaction.getOid() == null)
            return null;

        Category cat = categoryRepository.findOneByOid(transaction.getOid());
        if (cat == null) {
            log.info("Could not find Transaction by oid: {}", transaction.getOid());
            return null;
        }

        log.info("Partially updating Transaction with oid: {}", cat.getOid());

        PartialUpdateUtil.copyNonNullProperties(transaction, cat);
        cat.setModified(Date.from(Instant.now()));
        return categoryRepository.save(cat);
    }

    @Transactional(readOnly = true)
    public List<Category> getAllCategories() {

        List<Category> categories = categoryRepository.findAll();
        if (categories == null)
            log.info("Could not find any categories");

        return categories;
    }

    List<Category> getCategoriesFromDB(List<Category> categories){

        List<Category> cats = new ArrayList<>();

        for (Category cat : categories){
            Category item = categoryRepository.findOneByOid(cat.getOid());
            if (item!= null){
                cats.add(item);
            }
        }

        return cats.size() > 0 ? cats : null;

    }
}
