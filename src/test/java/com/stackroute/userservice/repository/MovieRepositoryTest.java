package com.stackroute.userservice.repository;

import com.stackroute.userservice.model.Movie;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    Movie movie;

    @Before
    public void setUp()
    {
        movie = new Movie();
        movie.setId(10);
        movie.setTitle("Manikarnika");
        movie.setOriginal_language("Hindi");
        movie.setOverview("About Jhansi rani");

    }

    @After
    public void tearDown(){

        movieRepository.deleteAll();
    }


    @Test
    public void testSaveMovie(){
     movieRepository.save(movie);
     Movie fetchMovie = movieRepository.findById(movie.getId()).get();
        Assert.assertEquals(java.util.Optional.of(10),java.util.Optional.of(fetchMovie.getId()));
    }

    @Test
    public void testSaveMovieFailure(){
        Movie testMovie = new Movie(4,"john","Based on True Story of John","Hindi");
        movieRepository.save(movie);
//        Movie fetchMovie = movieRepository.findById(movie.getId()).get();
        Assert.assertNotSame(testMovie,movie);
    }

    @Test
    public void testGetAllMovie(){
        Movie u = new Movie(5,"john1","Based on True Story of John part 1","Hindi");
        Movie u1 = new Movie(6,"john2","Based on True Story of John part 2","Hindi");
        movieRepository.save(u);
        movieRepository.save(u1);
        List<Movie> list = movieRepository.findAll();
        Assert.assertEquals("john1",list.get(0).getTitle());
    }


}
