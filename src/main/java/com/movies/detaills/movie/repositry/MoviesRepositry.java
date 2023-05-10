package com.movies.detaills.movie.repositry;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movies.detaills.movie.dto.RatingDTO;
import com.movies.detaills.movie.entity.Movies;
import com.movies.detaills.movie.entity.Ratings;

@Repository
@Transactional
public interface MoviesRepositry extends JpaRepository<Movies, String> {

	@Query(value = "select *from movies order by  runtimeminutes desc limit 10", nativeQuery = true)
	public List<Movies> getMovies();

	Movies findByTconst(String tconst);

	//	@Query(value = "select * from ratings , movies \r\n"
	//			+ " where ratings.tconst = movies.tconst\r\n"
	//			+ " and ratings.average_rating >= '6.0' ", nativeQuery = true)
	//	public List<Movies> getRatingMovie();

	@Query(value="UPDATE movies\r\n"
			+ "SET runtimeMinutes = \r\n"
			+ "    CASE \r\n"
			+ "        WHEN genres = 'Documentary' THEN runtimeMinutes + 15\r\n"
			+ "        WHEN genres = 'Animation' THEN runtimeMinutes + 30\r\n"
			+ "        ELSE runtimeMinutes + 45\r\n"
			+ "    END;" , nativeQuery = true)
	public void setruntime();


}
