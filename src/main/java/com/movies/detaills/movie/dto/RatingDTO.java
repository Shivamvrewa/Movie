package com.movies.detaills.movie.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RatingDTO {

	private String tconst;

	private String primaryTitle;

	private String genres;

	private Double averageRating;

}
