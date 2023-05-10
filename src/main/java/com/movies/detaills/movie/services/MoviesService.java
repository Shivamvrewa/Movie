package com.movies.detaills.movie.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.movies.detaills.movie.dto.MoviesDTO;
import com.movies.detaills.movie.dto.RatingDTO;
import com.movies.detaills.movie.entity.Movies;
import com.movies.detaills.movie.entity.Ratings;
import com.movies.detaills.movie.repositry.MoviesRepositry;
import com.movies.detaills.movie.repositry.RatingRepositry;


@Service
public class MoviesService {


	@Autowired
	private JdbcTemplate jdbcTemplate;



	@Autowired
	private MoviesRepositry moviesRepositry;

	@Autowired
	private RatingRepositry ratingRepositry;

	public List<MoviesDTO> getTopMovies() {
		List<Movies> list=moviesRepositry.getMovies();
		List<MoviesDTO> moviesDTOs=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			MoviesDTO dto=new MoviesDTO();
			dto.setTconst(list.get(i).getTconst());
			dto.setPrimaryTitle(list.get(i).getPrimarytitle());
			dto.setRuntimeMinutes(list.get(i).getRuntimeminutes());
			dto.setGenres(list.get(i).getGenres());
			moviesDTOs.add(dto);

		}
		return moviesDTOs;

	}

	public Boolean saveMovies(Movies movies) {
		String reandomid =UUID.randomUUID().toString();
		movies.setTconst(reandomid);
		movies.setTitletype(movies.getTitletype());
		movies.setGenres(movies.getGenres());
		movies.setRuntimeminutes(movies.getRuntimeminutes());
		movies.setPrimarytitle(movies.getPrimarytitle());
		moviesRepositry.save(movies);
		return true;
	}

	public List<RatingDTO> getRating(){
		List<Ratings> list=ratingRepositry.getRatings();

		List<RatingDTO> dtos=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			RatingDTO dto=new RatingDTO();
			Movies movies=moviesRepositry.findByTconst(list.get(i).getTconst());
			dto.setTconst(movies.getTconst());
			dto.setPrimaryTitle(movies.getPrimarytitle());
			dto.setGenres(movies.getGenres());
			dto.setAverageRating(list.get(i).getAveragerating());
			dtos.add(dto);
		}
		return dtos;
	}



	public int updateMovieRuntimes() {
		String sql = "UPDATE movies " +
				"SET runtimeMinutes = " +
				"    CASE " +
				"        WHEN genres = 'Documentary' THEN runtimeMinutes + 15 " +
				"        WHEN genres = 'Animation' THEN runtimeMinutes + 30 " +
				"        ELSE runtimeMinutes + 45 " +
				"    END";
		return jdbcTemplate.update(sql);
	}

	public List<Map<String, Object>> genreMoviesWithSubtotals() {
		String sql = "SELECT * FROM (\n" +
				"  SELECT m.genres, m.primarytitle, SUM(r.numvotes) AS numVotes\n" +
				"  FROM movies m\n" +
				"  JOIN ratings r ON m.tconst = r.tconst\n" +
				"  WHERE m.primarytitle IS NOT NULL\n" +
				"  GROUP BY GROUPING SETS ((m.genres, m.primarytitle), (m.genres))\n" +
				"  ORDER BY m.genres, numvotes\n" +
				") t\n" +
				"UNION ALL\n" +
				"SELECT *\n" +
				"FROM (\n" +
				"  SELECT m.genres, 'TOTAL' AS primarytitle, SUM(r.numvotes) AS numVotes\n" +
				"  FROM movies m\n" +
				"  JOIN ratings r ON m.tconst = r.tconst\n" +
				"  GROUP BY m.genres\n" +
				"  HAVING COUNT(*) > 0\n" +
				") t2\n" +
				"WHERE primarytitle IS NOT NULL\n" +
				"ORDER BY genres, numVotes";

		return jdbcTemplate.queryForList(sql);

	}

}
