package com.movies.detaills.movie.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class MoviesDTO {
	private String tconst;

	private String primaryTitle;

	private Long runtimeMinutes;

	private String genres;

}
