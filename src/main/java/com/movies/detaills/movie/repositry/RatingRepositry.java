package com.movies.detaills.movie.repositry;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.movies.detaills.movie.entity.Movies;
import com.movies.detaills.movie.entity.Ratings;

@Repository
@Transactional
public interface RatingRepositry extends JpaRepository<Ratings,String> {
	
	@Query(value = "select * from ratings where average_rating >= '6.0'", nativeQuery = true)
	public List<Ratings> getRatings();

}
