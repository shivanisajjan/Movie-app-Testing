package com.stackroute.userservice.service;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import java.util.List;

public interface MovieService {

    public boolean saveMovie(Movie movie) throws MovieExistsByIdGlobalException;
    public List<Movie> getallMovies();
    public boolean update(Movie movie) throws MovieNotFoundGlobalException;
    public boolean deleteMovie(int id) throws MovieNotFoundGlobalException;
    public List<Movie> getMoviesbyTitle(String title) throws MovieNotFoundGlobalException;
}
