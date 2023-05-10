package com.movies.detaills.movie.repositry;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.movies.detaills.movie.entity.Movies;

@Transactional
public interface MoviesRepositry extends JpaRepository<Movies, String> {

	@Query(value = "select *from movies order by  runtimeminutes desc limit 10", nativeQuery = true)
	public List<Movies> getMovies();

	Movies findByTconst(String tconst);

	



}
