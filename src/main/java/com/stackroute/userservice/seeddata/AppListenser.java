package com.stackroute.userservice.seeddata;

import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:appListener.properties", ignoreResourceNotFound=true)
public class AppListenser implements ApplicationListener<ContextRefreshedEvent> {
    @Value("${movie.id}")
    private int id;
    @Value("${movie.title}")
    private String title;
    @Value("${movie.original_language}")
    private String original_lan;
    @Value("${movie.overview}")
    private String overview;
    @Autowired
    private MovieRepository movieRepository;
    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        preFillData();
    }
    public void preFillData(){
        this.movieRepository.save(new Movie(id,title,overview,original_lan));
    }
}
