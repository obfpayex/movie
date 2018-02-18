package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Stopwatch;
import com.obf.movie.domain.Category;
import com.obf.movie.service.CategoryService;
import com.obf.movie.util.ResponseUtil;
//import com.payex.vas.common.vasutil.utils.//Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obf-movie-server/api")
public class CategoryRecource {

    private static final Logger log = LoggerFactory.getLogger(CategoryRecource.class);

    private final CategoryService categoryService;

    public CategoryRecource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @Timed
    @GetMapping(value = "/category/{oId}")
    public ResponseEntity<Category> getOneCategory(@PathVariable("oId") Long oId) {
        //Stopwatch //Stopwatch = //Stopwatch.createStarted();
        try {
            log.info("get invoked: {} ", oId);

            Category response = categoryService.getOneCategory(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }

    @Timed
    @PostMapping(value = "/category")
    public ResponseEntity<Category> addCategory(@RequestBody Category request) {
        //Stopwatch //Stopwatch = //Stopwatch.createStarted();
        try {
            log.info("add() invoked for category with oid: {} ", request.getOid());

            Category response = categoryService.saveNewCategory(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", "");
        }
    }

    @Timed
    @PutMapping(value = "/category")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid Category request) {
        //Stopwatch //Stopwatch = //Stopwatch.createStarted();
        try {
            log.info("update() invoked for category with oid: {} ", request.getOid());

            Category response = categoryService.updateCategory(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update","");
        }
    }

    @Timed
    @PatchMapping(value = "/category")
    public ResponseEntity<Category> partialUpdate(@RequestBody Category request) throws Exception {
        //Stopwatch //Stopwatch = //Stopwatch.createStarted();
        try {
            log.info("partialUpdate() invoked for transaction with oid: {} ", request.getOid());

            Category response = categoryService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", "");
        }
    }

    @Timed
    @GetMapping(value = "/category/getallcategory")
    public List<Category> getAllCategories() {
        //Stopwatch //Stopwatch = //Stopwatch.createStarted();
        try {
            //log.info("get invoked: {} ", originalTitle);

            List<Category> response = categoryService.getAllCategories();

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }
}
