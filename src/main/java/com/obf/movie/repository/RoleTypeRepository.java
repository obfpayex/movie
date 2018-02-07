package com.obf.movie.repository;

import com.obf.movie.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleTypeRepository extends JpaRepository<RoleType, Long> {

    RoleType findOneByOid(Long aLong);
}
