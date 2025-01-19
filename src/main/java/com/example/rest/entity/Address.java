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
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String doorno;
	private String street;
	private String city;
	private int pincode;
	
	public Address(String doorno, String street, String city, int pincode) {
	    this.doorno = doorno;
	    this.street = street;
	    this.city = city;
	    this.pincode = pincode;
	}

}
