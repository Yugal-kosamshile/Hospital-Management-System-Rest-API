package com.example.rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctors {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String name; 
	private String specialization;
	private String location;
	private String gender;
}
