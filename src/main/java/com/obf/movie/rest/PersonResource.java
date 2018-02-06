package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.domain.Person;
import com.obf.movie.service.PersonService;
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
public class PersonResource {

    private static final Logger log = LoggerFactory.getLogger(PersonResource.class);

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }


    @Timed
    @GetMapping(value = "/person/{oId}")
    public ResponseEntity<Person> getOnePerson(@PathVariable("oId") Long oId) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("get invoked: {} ", oId);

            Person response = personService.getOnePerson(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", stopwatch.stop().toString());
        }
    }

    @Timed
    @PostMapping(value = "/person")
    public ResponseEntity<Person> addMovie(@RequestBody Person request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("add() invoked for person with oid: {} ", request.getOid());

            Person response = personService.saveNewPerson(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", stopwatch.stop().toString());
        }
    }

    @Timed
    @PutMapping(value = "/person")
    public ResponseEntity<Person> update(@RequestBody @Valid Person request) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("update() invoked for person with oid: {} ", request.getOid());

            Person response = personService.updatePerson(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", stopwatch.stop().toString());
        }
    }

    @Timed
    @PatchMapping(value = "/person")
    public ResponseEntity<Person> partialUpdate(@RequestBody Person request) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        try {
            log.info("partialUpdate() invoked for Person with oid: {} ", request.getOid());

            Person response = personService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", stopwatch.stop().toString());
        }
    }
}
