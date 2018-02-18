package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.domain.Country;
import com.obf.movie.service.CountryService;
import com.obf.movie.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/obf-movie-server/api")
public class CountryRecource {

    private static final Logger log = LoggerFactory.getLogger(CountryRecource.class);

    private final CountryService countryService;

    public CountryRecource(CountryService countryService) {
        this.countryService = countryService;
    }


    @Timed
    @GetMapping(value = "/country/{oId}")
    public ResponseEntity<Country> getOneCountry(@PathVariable("oId") Long oId) {

        try {
            log.info("get invoked: {} ", oId);

            Country response = countryService.getOneCountry(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }

    @Timed
    @PostMapping(value = "/country")
    public ResponseEntity<Country> addLanguage(@RequestBody Country request) {

        try {
            log.info("add() invoked for Country with oid: {} ", request.getOid());

            Country response = countryService.saveNewCountry(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", "");
        }
    }

    @Timed
    @PutMapping(value = "/country")
    public ResponseEntity<Country> updateCountry(@RequestBody @Valid Country request) {

        try {
            log.info("update() invoked for Country with oid: {} ", request.getOid());

            Country response = countryService.updateCountry(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", "");
        }
    }

    @Timed
    @PatchMapping(value = "/country")
    public ResponseEntity<Country> partialUpdate(@RequestBody Country request) throws Exception {

        try {
            log.info("partialUpdate() invoked for transaction with oid: {} ", request.getOid());

            Country response = countryService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", "");
        }
    }
    @Timed
    @GetMapping(value = "/country/getalllanguages")
    public List<Country> getAllLanguages() {

        try {
            //log.info("get invoked: {} ", originalTitle);

            List<Country> response = countryService.getAllCountrys();

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }
}
