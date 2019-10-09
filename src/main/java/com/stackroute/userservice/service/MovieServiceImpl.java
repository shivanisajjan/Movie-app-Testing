package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
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

    @Override
    public List<Movie> getallMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    public boolean update(Movie movie) throws MovieNotFoundGlobalException {
        Movie movie1=this.movieRepository.save(movie);
        if(movie1==null){
            throw new MovieNotFoundGlobalException();
        }
        return true;
    }

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


    @Override
    public List<Movie> getMoviesbyTitle(String title) throws MovieNotFoundGlobalException{
        List<Movie> li=this.movieRepository.findBytitle(title);
        if(li.isEmpty()){
            throw new MovieNotFoundGlobalException();
        }
        return li;
    }
}
