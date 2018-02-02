package com.obf.movie.repository;

import com.obf.movie.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findOneByOid(Long aLong);
}
