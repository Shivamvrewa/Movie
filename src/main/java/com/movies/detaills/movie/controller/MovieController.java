package com.movies.detaills.movie.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.detaills.movie.entity.Movies;
import com.movies.detaills.movie.services.MoviesService;


@RestController
@RequestMapping("/api/v1")
public class MovieController {

	@Autowired
	private MoviesService moviesService;


	@GetMapping(value = "/longest-duration-movies" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> getTopMovies(){
		try {
			return new ResponseEntity<>(moviesService.getTopMovies(),HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping(value = "/new-movie" , produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> addMovie(@RequestBody Movies movies){
		try {
			moviesService.saveMovies(movies);
			//			return new ResponseEntity<>(moviesService.saveMovies(movies),HttpStatus.CREATED);
			return ResponseEntity.ok("sucess");
		} catch (Exception e) {

			return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
		}

	}

	@GetMapping(value = "/top-rated-movies" , produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> getRatingMovies(){
		try {
			return new ResponseEntity<>(moviesService.getRating(),HttpStatus.OK);
		} catch (Exception e) {

			return new ResponseEntity<>(e,HttpStatus.BAD_REQUEST);
		}

	}


	@GetMapping("/genre-movies-with-subtotals")
	public ResponseEntity<Object> getTotalmovies() {
		try {
			List<Map<String, Object>> numUpdated = moviesService.get();
			return ResponseEntity.ok(numUpdated );
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PostMapping(value = "/update-runtime-minutes", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateRuntimeMinutes() {
		try {
			int numUpdated = moviesService.updateMovieRuntimes();
			return ResponseEntity.ok("Updated " + numUpdated + " movies");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

}




