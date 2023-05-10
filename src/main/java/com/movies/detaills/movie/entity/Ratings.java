package com.movies.detaills.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity(name = "Ratings")
public class Ratings {
	
	@Id
	private String tconst;
	
	private Double averagerating;
	
	private Integer numvotes;
	

}
