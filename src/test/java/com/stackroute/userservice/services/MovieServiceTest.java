package com.stackroute.userservice.services;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import com.stackroute.userservice.service.MovieServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.exceptions.base.MockitoException;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class MovieServiceTest {

    Movie movie;

    //Create a mock for MovieRepository
    @Mock
    MovieRepository movieRepository;

    //Inject the mocks as dependencies into MovieServiceImpl
    @InjectMocks
    MovieServiceImpl movieService;
    List<Movie> list= null;


    @Before
    public void setUp(){
        //Initialising the mock object
        MockitoAnnotations.initMocks(this);
        movie = new Movie();
        movie = new Movie();
        movie.setId(15);
        movie.setTitle("War");
        movie.setOriginal_language("Hindi");
        movie.setOverview("An exhausting film to watch in the best sense, venting our anger at the dehumanizing forces in society until we are left drained.");
        list = new ArrayList();
        list.add(movie);


    }

    @Test
    public void saveMovieTestSuccess() throws MovieExistsByIdGlobalException {

        when(movieRepository.save(any())).thenReturn(movie);
        Boolean savedMovie = movieService.saveMovie(movie);
        Assert.assertEquals(true,savedMovie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);
      
    }

    @Test(expected = MovieExistsByIdGlobalException.class)
    public void saveMovieTestFailure() throws MovieExistsByIdGlobalException {
        when(movieRepository.save(any())).thenReturn(null);
        Boolean savedMovie = movieService.saveMovie(movie);
//        System.out.println("savedMovie" + savedMovie);
        //Assert.assertEquals(movie,savedMovie);

    }
    @Test(expected = MockitoException.class)
    public void saveMovieTestFailure1() throws MovieExistsByIdGlobalException {
//        when(movieRepository.findById(eq(15))).thenThrow(MovieExistsByIdGlobalException.class);
        doThrow(new MovieExistsByIdGlobalException()).when(movieRepository).findById(eq(15));
        Boolean savedMovie = movieService.saveMovie(movie);
        //Assert.assertEquals(movie,savedMovie);
    }

    @Test
    public void getAllMovie(){

        movieRepository.save(movie);
        //stubbing the mock to return specific data
        when(movieRepository.findAll()).thenReturn(list);
        List<Movie> movielist = movieService.getallMovies();
        Assert.assertEquals(list,movielist);
    }
    @Test
    public void updateMovieTestSuccess() throws MovieNotFoundGlobalException {

        when(movieRepository.save(any())).thenReturn(movie);
        Boolean savedMovie = movieService.update(movie);
        Assert.assertEquals(true,savedMovie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).save(movie);

    }
    @Test(expected = MovieNotFoundGlobalException.class)
    public void updateMovieTestFailure() throws MovieNotFoundGlobalException {
        when(movieRepository.save(any())).thenReturn(null);
        Boolean savedMovie = movieService.update(movie);
    }
    @Test
    public void deleteMovieTestSuccess() throws MovieNotFoundGlobalException {
        when(movieRepository.existsById(anyInt())).thenReturn(true);
        Boolean deleteMovie = movieService.deleteMovie(12);
        Assert.assertEquals(true,deleteMovie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).existsById(12);
        verify(movieRepository,times(1)).deleteById(12);
    }
    @Test(expected = MovieNotFoundGlobalException.class)
    public void deleteMovieTestFailure() throws MovieNotFoundGlobalException {
        when(movieRepository.existsById(anyInt())).thenReturn(false);
        Boolean deleteMovie = movieService.deleteMovie(15);
    }
    @Test
    public void getMovieByTitleTestSuccess() throws MovieNotFoundGlobalException {
        when(movieRepository.findBytitle(any())).thenReturn(list);
        List<Movie> getMovie = movieService.getMoviesbyTitle("war");
        Assert.assertEquals(list,getMovie);
        //verify here verifies that movieRepository save method is only called once
        verify(movieRepository,times(1)).findBytitle("war");
    }
    @Test(expected = MovieNotFoundGlobalException.class)
    public void getMovieByTitleTestFailure() throws MovieNotFoundGlobalException {
        when(movieRepository.findBytitle(any())).thenReturn(new ArrayList<>());
        Boolean deleteMovie = movieService.deleteMovie(15);
    }

}
