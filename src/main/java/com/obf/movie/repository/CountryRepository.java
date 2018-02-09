package com.obf.movie.repository;

import com.obf.movie.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    Country findOneByOid(Long aLong);
}
