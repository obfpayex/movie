package com.obf.movie.service;


import com.obf.movie.domain.Category;
import com.obf.movie.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional(readOnly = true)
    public Category getOneCategory(Long oid) {

        Category tx = categoryRepository.findOneByOid(oid);
        if (tx == null)
            log.info("Could not find Category by oid: {}", oid);

        return tx;
    }

    @Transactional
    public Category saveNewCategory(Category Category) {
        log.info("Saving Category");
        Category.setModified(Date.from(Instant.now()));
        Category.setCreated(Date.from(Instant.now()));
        categoryRepository.save(Category);
        return Category;
    }

    @Transactional
    public Category updateCategory(Category Category) {

        Category mov = categoryRepository.findOneByOid(Category.getOid());
        if (mov == null) {
            log.info("Could not find Category by oid: {}", Category.getOid());
            return null;
        }

        log.info("Updating Category with oid: {}", mov.getOid());
        Category.setModified(Date.from(Instant.now()));
        Category = categoryRepository.save(Category);

        return Category;
    }
}
