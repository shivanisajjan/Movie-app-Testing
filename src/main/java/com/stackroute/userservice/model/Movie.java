package com.stackroute.userservice.model;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;

@Entity(name = "Movie")
@ApiModel(description = "All details about the Movie. ")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    private Integer id;
    private String title;
    private String overview;
    private String original_language;
//    private List<Integer> genre_ids;

}
