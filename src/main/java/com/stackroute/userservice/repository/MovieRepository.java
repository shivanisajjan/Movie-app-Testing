package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {

    //query for getting list of movie by given title
    @Query(value = "SELECT * FROM Movie m WHERE m.title = :title", nativeQuery=true)
    List<Movie> findByTitle(@Param("title") String title);
}
