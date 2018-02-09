package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.domain.Language;
import com.obf.movie.service.LanguageService;
import com.obf.movie.util.ResponseUtil;
import com.payex.vas.common.vasutil.utils.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obf-movie-server/api")
public class LanguageRecource {

    private static final Logger log = LoggerFactory.getLogger(MovieResource.class);

    private final LanguageService languageService;

    public LanguageRecource(LanguageService languageService) {
        this.languageService = languageService;
    }


    @Timed
    @GetMapping(value = "/language/{oId}")
    public ResponseEntity<Language> getOneLanguage(@PathVariable("oId") Long oId) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("get invoked: {} ", oId);

            Language response = languageService.getOneLanguage(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", stopwatch.stop().toString());
        }
    }

    @Timed
    @PostMapping(value = "/language")
    public ResponseEntity<Language> addLanguage(@RequestBody Language request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("add() invoked for Language with oid: {} ", request.getOid());

            Language response = languageService.saveNewLanguage(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", stopwatch.stop().toString());
        }
    }

    @Timed
    @PutMapping(value = "/language")
    public ResponseEntity<Language> updateLanguage(@RequestBody @Valid Language request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("update() invoked for Language with oid: {} ", request.getOid());

            Language response = languageService.updateLanguage(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", stopwatch.stop().toString());
        }
    }

    @Timed
    @PatchMapping(value = "/language")
    public ResponseEntity<Language> partialUpdate(@RequestBody Language request) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("partialUpdate() invoked for transaction with oid: {} ", request.getOid());

            Language response = languageService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", stopwatch.stop().toString());
        }
    }
    @Timed
    @GetMapping(value = "/language/getalllanguages")
    public List<Language> getAllLanguages() {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            //log.info("get invoked: {} ", originalTitle);

            List<Language> response = languageService.getAllLanguages();

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", stopwatch.stop().toString());
        }
    }
}
