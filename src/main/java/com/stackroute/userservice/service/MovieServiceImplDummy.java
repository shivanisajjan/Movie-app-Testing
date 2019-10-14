package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Profile("prod")
public class MovieServiceImplDummy implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImplDummy(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    //Service method to save a movie
    @Override
    public boolean saveMovie(Movie movie) throws MovieExistsByIdGlobalException {
        System.out.println("Dummy");
        if(this.movieRepository.existsById(movie.getId())){
            throw new MovieExistsByIdGlobalException();
        }
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieExistsByIdGlobalException();
        }
        System.out.println("");
        return true;
    }

    //service method to get list of movies
    @Override
    public List<Movie> getAllMovies()
    {
        System.out.println("Dummy");
        return this.movieRepository.findAll();
    }

    //service method to update a movie
    @Override
    public boolean update(Movie movie) throws MovieNotFoundGlobalException {
        System.out.println("Dummy");
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieNotFoundGlobalException();
        }
        return true;
    }

    //service method t delete a movie by Id
    @Override
    public boolean deleteMovie(int id) throws MovieNotFoundGlobalException{
        System.out.println("Dummy");
        if(this.movieRepository.existsById(id)){
            this.movieRepository.deleteById(id);
            return true;
        }
        else{
            throw new MovieNotFoundGlobalException();
        }
    }

    //service method to get list of movies by title
    @Override
    public List<Movie> getMoviesByTitle(String title) throws MovieNotFoundGlobalException{
        System.out.println("Dummy");
        List<Movie> li=this.movieRepository.findByTitle(title);
        if(li.isEmpty()){
            throw new MovieNotFoundGlobalException();
        }
        return li;
    }
}
