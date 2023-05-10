package com.movies.detaills.movie.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.TableGenerator;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter

@Entity(name = "Movies")
public class Movies {
	@Id
	private String tconst;
	
	private String titletype;
	
	private String primarytitle;
	
	private Long runtimeminutes;
	
	private String genres;
	
	
	
	
	

}
