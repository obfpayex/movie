package com.obf.movie.service;

import com.obf.movie.domain.Person;
import com.obf.movie.repository.PersonRepository;
import com.obf.movie.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Transactional(readOnly = true)
    public Person getOnePerson(Long oid) {

        Person per = personRepository.findOneByOid(oid);
        if (per == null)
            log.info("Could not find Person by oid: {}", oid);

        return per;
    }

    @Transactional
    public Person saveNewPerson(Person person) {
        log.info("Saving Person");

        person.setModified(Date.from(Instant.now()));
        person.setCreated(Date.from(Instant.now()));
        personRepository.save(person);
        return person;
    }

    @Transactional
    public Person updatePerson(Person person) {

        Person per = personRepository.findOneByOid(person.getOid());
        if (per == null) {
            log.info("Could not find person by oid: {}", person.getOid());
            return null;
        }
        log.info("Updating person with oid: {}", per.getOid());
        person.setModified(Date.from(Instant.now()));
        return personRepository.save(person);
    }

    @Transactional
    public Person partialUpdate(Person person) throws Exception {
        if (person.getOid() == null)
            return null;

        Person per = personRepository.findOneByOid(person.getOid());
        if (per == null) {
            log.info("Could not find Transaction by oid: {}", person.getOid());
            return null;
        }

        log.info("Partially updating Transaction with oid: {}", per.getOid());

        PartialUpdateUtil.copyNonNullProperties(person, per);
        per.setModified(Date.from(Instant.now()));
        per = personRepository.save(per);

        return per;
    }

    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {

        List<Person> people = personRepository.findAll();
        if (people == null)
            log.info("Could not find any people");

        return people;
    }

    Person getPersonFromDB(Person item) {
        Person person = personRepository.findOneByOid(item.getOid());
        return person!= null? person : item;
    }
}
