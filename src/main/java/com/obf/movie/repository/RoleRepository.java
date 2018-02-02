package com.obf.movie.repository;

import com.obf.movie.domain.Category;
import com.obf.movie.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findOneByOid(Long aLong);
}
