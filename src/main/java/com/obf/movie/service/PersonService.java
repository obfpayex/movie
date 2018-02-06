package com.obf.movie.service;

import com.obf.movie.domain.Category;
import com.obf.movie.domain.Movie;
import com.obf.movie.domain.Person;
import com.obf.movie.domain.Role;
import com.obf.movie.repository.CategoryRepository;
import com.obf.movie.repository.MovieRepository;
import com.obf.movie.repository.PersonRepository;
import com.obf.movie.repository.RoleRepository;
import com.obf.movie.util.PartialUpdateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    private static final Logger log = LoggerFactory.getLogger(PersonService.class);

    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
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

//        Set<Role> roles = person.getRoles().stream().collect(Collectors.toSet());
//        person.getRoles().clear();
//        for (Role rol : roles){
//            Role item = roleRepository.findOneByOid(rol.getOid());
//            if (item!= null){
//                person.getRoles().add(item);
//            } else {
//                person.getRoles().add(rol);
//            }
//        }
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

}
