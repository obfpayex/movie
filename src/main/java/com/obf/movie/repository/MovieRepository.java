package com.obf.movie.repository;

import com.obf.movie.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Movie findOneByOid(Long aLong);

    List<Movie> findByOriginalTitle(String originalTitle);

    @Query("select m from Movie m where m.originalTitle like %?1%")
    List<Movie> findByOriginalTitleLike(String like);

}
