package com.obf.movie.repository;

import com.obf.movie.domain.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {

    Language findOneByOid(Long aLong);
}
