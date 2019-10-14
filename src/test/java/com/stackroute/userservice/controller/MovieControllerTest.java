package com.stackroute.userservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.userservice.exceptions.MovieExistsByIdGlobalException;
import com.stackroute.userservice.exceptions.MovieNotFoundGlobalException;
import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.service.MovieService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest
public class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private Movie movie;
    @MockBean
    private MovieService movieService;
    @InjectMocks
    private MovieController movieController;

    private List<Movie> list =null;

    @Before
    public void setUp(){

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).setControllerAdvice(new MovieExceptionController()).build();
        movie = new Movie();
        movie.setId(26);
        movie.setTitle("War");
        movie.setOriginalLanguage("Hindi");
        movie.setOverview("An exhausting film to watch in the best sense, venting our anger at the dehumanizing forces in society until we are left drained.");
        list = new ArrayList();
        list.add(movie);
    }

    @Test
    public void saveMovie() throws Exception {
        when(movieService.saveMovie(any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andDo(MockMvcResultHandlers.print());


    }
      @Test
     public void saveMovieFailure() throws Exception {
        when(movieService.saveMovie(any())).thenThrow(MovieExistsByIdGlobalException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/movie")
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void getAllMovie() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movie")
        .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void updateMovie() throws Exception {
        when(movieService.update((Movie) any())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());


    }
    @Test
    public void updateMovieFailure() throws Exception {
        when(movieService.update(any())).thenThrow(MovieNotFoundGlobalException.class);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/movie")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteMovie() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movie/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void deleteMovieFailure() throws Exception {
        when(movieService.deleteMovie(anyInt())).thenThrow(MovieNotFoundGlobalException.class);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/movie/1")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getMovieByTitle() throws Exception {
        when(movieService.getMoviesByTitle(anyString())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movie/title/war")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void getMovieByTitleFailure() throws Exception {
        when(movieService.getMoviesByTitle(anyString())).thenThrow(MovieNotFoundGlobalException.class);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/movie/title/war")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(movie)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String asJsonString(final Object obj)
    {
        try{
            return new ObjectMapper().writeValueAsString(obj);

        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }










}
