package com.obf.movie.rest;


import com.codahale.metrics.annotation.Timed;
import com.obf.movie.domain.Person;
import com.obf.movie.service.PersonService;
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
public class PersonResource {

    private static final Logger log = LoggerFactory.getLogger(PersonResource.class);

    private final PersonService personService;

    public PersonResource(PersonService personService) {
        this.personService = personService;
    }


    @Timed
    @GetMapping(value = "/person/{oId}")
    public ResponseEntity<Person> getOnePerson(@PathVariable("oId") Long oId) {

        try {
            log.info("get invoked: {} ", oId);

            Person response = personService.getOnePerson(oId);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }

    @Timed
    @PostMapping(value = "/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person request) {

        try {
            log.info("add() invoked for person with oid: {} ", request.getOid());

            Person response = personService.saveNewPerson(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "add", "");
        }
    }

    @Timed
    @PutMapping(value = "/person")
    public ResponseEntity<Person> updatePerson(@RequestBody @Valid Person request) {

        try {
            log.info("update() invoked for person with oid: {} ", request.getOid());

            Person response = personService.updatePerson(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "update", "");
        }
    }

    @Timed
    @PatchMapping(value = "/person")
    public ResponseEntity<Person> partialUpdatePerson(@RequestBody Person request) throws Exception {

        try {
            log.info("partialUpdate() invoked for Person with oid: {} ", request.getOid());

            Person response = personService.partialUpdate(request);
            return ResponseUtil.wrapOrNotFound(Optional.ofNullable(response));
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "partialUpdate", "");
        }
    }

    @Timed
    @GetMapping(value = "/person/getallpersons")
    public List<Person> getAllpersons() {

        try {
            //log.info("get invoked: {} ", originalTitle);

            List<Person> response = personService.getAllPersons();

            return response;
        } catch (Exception ex) {
            log.error("Something happened {}", ex.getMessage(), ex);
            throw ex;
        } finally {
            log.info("# finished [{}] executeTime : {}", "get", "");
        }
    }
}
