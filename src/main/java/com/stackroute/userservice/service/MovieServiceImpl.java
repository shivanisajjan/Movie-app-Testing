package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Primary
@Profile("dev")
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    //Service method to save a movie
    @Override
    public boolean saveMovie(Movie movie) throws MovieExistsByIdGlobalException {
        if(this.movieRepository.existsById(movie.getId())){
            throw new MovieExistsByIdGlobalException();
        }
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieExistsByIdGlobalException();
        }
        return true;
    }

    //service method to get list of movies
    @Override
    public List<Movie> getAllMovies() {
        return this.movieRepository.findAll();
    }

    //service method to update a movie
    @Override
    public boolean update(Movie movie) throws MovieNotFoundGlobalException {
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieNotFoundGlobalException();
        }
        return true;
    }

    //service method t delete a movie by Id
    @Override
    public boolean deleteMovie(int id) throws MovieNotFoundGlobalException{
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
        List<Movie> movieList=this.movieRepository.findByTitle(title);
        if(movieList.isEmpty()){
            throw new MovieNotFoundGlobalException();
        }
        return movieList;
    }
}
