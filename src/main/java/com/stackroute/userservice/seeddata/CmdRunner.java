package com.stackroute.userservice.seeddata;

import com.stackroute.userservice.model.Movie;
import com.stackroute.userservice.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:cmdRunner.properties", ignoreResourceNotFound=true)
public class CmdRunner implements CommandLineRunner {
    @Value("${movie1.id}")
    private int id;
    @Value("${movie1.title}")
    private String title;
    @Value("${movie1.original_language}")
    private String original_lan;
    @Value("${movie1.overview}")
    private String overview;
    @Autowired
    private MovieRepository movieRepository;
    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public void preFillData(){
        this.movieRepository.save(new Movie(id,title,overview,original_lan));
    }

    @Override
    public void run(String... args) throws Exception {
        preFillData();
    }
}
