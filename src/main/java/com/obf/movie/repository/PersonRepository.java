package com.obf.movie.repository;

import com.obf.movie.domain.Category;
import com.obf.movie.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    Person findOneByOid(Long aLong);
}
