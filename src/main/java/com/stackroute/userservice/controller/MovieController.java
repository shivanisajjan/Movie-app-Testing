package com.stackroute.userservice.controller;

import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@Api(value="Movie Services", description="All Operations of Movie app")
public class MovieController {
    @Autowired
//    @Qualifier("MovieServiceImpl")
    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }
    @ApiOperation(value = "Add a Movie")
    @PostMapping("movie")
    public ResponseEntity<?> saveMovie(@ApiParam(value = "Movie object store in database table", required = true) @Valid @RequestBody Movie movie) throws MovieExistsByIdGlobalException {
        ResponseEntity responseEntity;
        movieService.saveMovie(movie);
        responseEntity = new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.CREATED);
        return responseEntity;
    }

    @ApiOperation(value = "View a list of available Movies", response = Movie.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("movie")
    public List<Movie> getAllMovie(){
        return (this.movieService.getallMovies());
    }

    @ApiOperation(value = "Update a Movie")
    @PutMapping("movie")
    public ResponseEntity<?> updateMovie(@ApiParam(value = "Update Movie object", required = true) @Valid @RequestBody Movie movie) throws MovieNotFoundGlobalException{
        ResponseEntity responseEntity;
        movieService.update(movie);
        responseEntity=new ResponseEntity<List<Movie>>(this.movieService.getallMovies(), HttpStatus.OK);
        return responseEntity;
    }


    @DeleteMapping("movie/{id}")
    @ApiOperation(value = "Delete an Movie")
    public ResponseEntity<?> deleteMovie(@ApiParam(value = "Movie Id from which Movie object will delete from database table", required = true) @PathVariable(value = "id") int id) throws MovieNotFoundGlobalException {
        ResponseEntity responseEntity;
        movieService.deleteMovie(id);
        responseEntity=new ResponseEntity<String>("Successfully deleted", HttpStatus.OK);
        return responseEntity;
    }

    @ApiOperation(value = "Get an Movie by Title")
    @GetMapping("movie/title/{title}")
    public ResponseEntity<?> getMovieByTitle(@ApiParam(value = "Movie title from which movie object will retrieve", required = true) @PathVariable(value = "title") String title) throws MovieNotFoundGlobalException {
        ResponseEntity responseEntity;
        responseEntity=new ResponseEntity<List<Movie>>(this.movieService.getMoviesbyTitle(title), HttpStatus.OK);
        return responseEntity;
    }
}
