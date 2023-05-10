package com.movies.detaills.movie.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.movies.detaills.movie.entity.Ratings;

@Transactional
public interface RatingRepositry extends JpaRepository<Ratings,String> {
	
	@Query(value = "select * from ratings where averagerating > '6.0'", nativeQuery = true)
	public List<Ratings> getRatings();

}
