package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.service.InitTestDataService;
import com.obf.movie.util.ResponseUtil;
import com.payex.vas.common.vasutil.utils.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/obf-movie-server/api")
public class InitTestDataRecource {

    private static final Logger log = LoggerFactory.getLogger(MovieResource.class);

    private final InitTestDataService initTestDataService;

    public InitTestDataRecource(InitTestDataService initTestDataService) {
        this.initTestDataService = initTestDataService;
    }


    @Timed
    @PutMapping(value = "/InitTestData/{oId}")
    public void initData(@PathVariable("oId") Long oId) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("update() invoked for movie with oid: {} ", + oId);

            initTestDataService.initData(oId);
            //return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", stopwatch.stop().toString());
        }
    }
}
