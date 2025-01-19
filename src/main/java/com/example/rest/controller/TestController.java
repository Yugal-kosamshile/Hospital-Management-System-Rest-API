package com.example.rest.controller;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.RestController; 

import com.example.rest.apiresponse.ApiResponse;
import com.example.rest.entity.Address;
import com.example.rest.entity.Hospital;
import com.example.rest.hospitalservice.HospitalService;
 
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/hospital")

public class TestController {
	
	@Autowired
	HospitalService hospitalService;
	
		/***** POST MAPPING *****/
	
	//1. Add new hospital details
	@PostMapping("/savedetails")
	public ResponseEntity<Hospital> saveDetails(@Valid @RequestBody Hospital hospital) {
	    Hospital savedDetails = hospitalService.saveHospitalDetail(hospital);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .header("info", "Data Saved Successfully! By Yugalk :) ")
	            .body(savedDetails);
	}

	
	//2. Add multiple hospital details
	@PostMapping("/savebulk")
	public ResponseEntity<List<Hospital>> postBulkDetails(@RequestBody List<Hospital> hospital) 
	{
		List<Hospital> savedDetails = hospitalService.saveBulkHospital(hospital);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .header("info", "Data Saved Successfully! By Yugalk :) ")
	            .body(savedDetails);
	}
	
			/***** GET MAPPING *****/
	
	//3. get all hospitals
	@GetMapping("/getall")
	public ResponseEntity<?>  getAllHospital() {
		List<Hospital> list = hospitalService.getAllHospital();
		if(!list.isEmpty()) {
		return ResponseEntity.status(HttpStatus.OK)
	            .header("info", "Data Retrieved Successfully! By Yugalk :)")
	            .body(list);
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponse.setMessage("Hospitals not found! By Yugalk :( " );
			apiResponse.setTimeStamp(LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            .header("info", "Data Not Found! By Yugalk :(")
		            .body(apiResponse);
		}
	}
	
	//4. get hospital by id
	@GetMapping("/getby-id/{id}")
	public ResponseEntity<?>  getHospitalById(@PathVariable ("id") Long id) 
	{
		Optional<Hospital> hospital = hospitalService.getById(id);
		if(hospital.isPresent())
		{
		return ResponseEntity.status(HttpStatus.OK)
	            .header("info", "Data Retrieved Successfully! By Yugalk :)")
	            .body(hospital.get());
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponse.setMessage("Hospital not found with id: " + id+" By Yugalk :(");
			apiResponse.setTimeStamp(LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            .header("info", "Data Not Found! By Yugalk :(")
		            .body(apiResponse);
		}
	}
	
	//5. get hospital by name
	@GetMapping("/getby-name/{name}")
	public ResponseEntity<?>  getHospitalByName(@PathVariable ("name") String name) 
	{
		 List<Hospital> hospital = hospitalService.getByName(name);
		if(!hospital.isEmpty())
		{
		return ResponseEntity.status(HttpStatus.OK)
	            .header("info", "Data Retrieved Successfully! By Yugalk :)")
	            .body(hospital);
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponse.setMessage("Hospital not found with name: " + name+" By Yugalk :(");
			apiResponse.setTimeStamp(LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            .header("info", "Data Not Found! By Yugalk :(")
		            .body(apiResponse);
		}
	}
			
	//6. get hospital by location
	@GetMapping("/getby-location/{location}")
	public ResponseEntity<?>  getHospitalByLocation(@PathVariable ("location") String location) 
	{
		 List<Hospital> hospital = hospitalService.getByLocation(location);
		if(!hospital.isEmpty())
		{
		return ResponseEntity.status(HttpStatus.OK)
	            .header("info", "Data Retrieved Successfully! By Yugalk :)")
	            .body(hospital);
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
			apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
			apiResponse.setMessage("Hospital not found with location: " + location+" By Yugalk :(");
			apiResponse.setTimeStamp(LocalDateTime.now());
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
		            .header("info", "Data Not Found! By Yugalk :(")
		            .body(apiResponse);
		}
	}
	
			
	//7. get hospitals by exact address
	@GetMapping("/getby-address/{doorno}/{street}/{city}/{pincode}")
	public ResponseEntity<?> getByLocation(
			@PathVariable("doorno") String doorno,
			@PathVariable("street") String street,
			@PathVariable("city") String city,
			@PathVariable("pincode") int pincode
	) {
	    
	    Address address = new Address(doorno, street, city, pincode);

	    List<Hospital> hospitals = hospitalService.getByAddress(address);

	    if (!hospitals.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully! By Yugalk :)")
	                .body(hospitals);
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("Hospitals with address : " + address + " not found! By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "Data not found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

				
	//8. get hospital details by rating
	@GetMapping("/getby-rating/{minRating}/{maxRating}")
	public ResponseEntity<?> getHospitalsByRatingRange(@PathVariable("minRating") int minRating,
	                                                   @PathVariable("maxRating") int maxRating) {
	    Optional<List<Hospital>> hospital = hospitalService.getByRating(minRating, maxRating);
	    if (hospital.isPresent() && !hospital.get().isEmpty()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Data retrieved successfully! By Yugalk :)")
	                .body(hospital.get());
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("Hospitals not found with ratings between: " + minRating + " and " + maxRating+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "Data not found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

				
				
	//9. Retrieve hospitals with a rating greater than a specified value
	@GetMapping("/rating/greater-than/{rating}")
	public ResponseEntity<?> getHospitalsWithRatingGreaterThan(@PathVariable("rating") int rating) {
	    List<Hospital> hospitals = hospitalService.findHospitalsWithRatingGreaterThan(rating);
	    if (!hospitals.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospitals retrieved successfully! By Yugalk :)")
	                .body(hospitals);
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found with a rating greater than: " + rating+ " By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	//10. Retrieve hospitals with a rating less than a specified value
	@GetMapping("/rating/less-than/{rating}")
	public ResponseEntity<?> getHospitalsWithRatingLessThan(@PathVariable("rating") int rating) {
	    List<Hospital> hospitals = hospitalService.findHospitalsWithRatingLessThan(rating);
	    if (!hospitals.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospitals retrieved successfully! By Yugalk :)")
	                .body(hospitals);
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found with a rating less than: " + rating+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	//11. Find by Location AND Name (Both conditions must be true)
	//http://localhost:8084/hospital/search?location=Downtown&name=City%20Hospital
	@GetMapping("/search")
	public ResponseEntity<?> findHospitalsByLocationAndName(@RequestParam("location") String location,
															@RequestParam("name") String name) {
	    List<Hospital> hospitals = hospitalService.findHospitalsByLocationAndName(location, name);
	    if (!hospitals.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospitals retrieved successfully! By Yugalk :)")
	                .body(hospitals);
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found in location: " + location + " with name: " + name+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	//12. Find by Location OR Name (At least one condition must be true)
	
	//http://localhost:8084/hospital/search-or?location=LOCATION&name=HOSPITAL-NAME
	
	@GetMapping("/search-or")
	public ResponseEntity<?> findHospitalsByLocationOrName(@RequestParam("location") String location,
															@RequestParam("name") String name) {
	    List<Hospital> hospitals = hospitalService.findHospitalsByLocationOrName(location, name);
	    if (!hospitals.isEmpty()) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospitals retrieved successfully! By Yugalk :)")
	                .body(hospitals);
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found in location: " + location + " or with name: " + name+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	//13. GET- hospitals located in a specific location and having a rating within a certain range using JPQL.
	@GetMapping("/location/{location}/minRating/{minRating}/maxRating/{maxRating}")
	public ResponseEntity<?> getHospitalsLocationMinMaxRatin(@PathVariable ("location") String location, 
															@PathVariable ("minRating") int minRating, 
															@PathVariable ("maxRating") int maxRating)
	{ 
		List<Hospital> hospitals=hospitalService.getbyLocationAndRatingBetween(location, minRating, maxRating);
		if(hospitals.isEmpty()) {
			
			ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found in location: " + location + " with Min rating : " + minRating+" max rating :"+maxRating+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
			} 
		return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospitals retrieved successfully! By Yugalk :)")
	                .body(hospitals);

	} 
	//15. GET - hospital in ascending order of rating(native SQL query)
	@GetMapping("/sortby-rating")
	public ResponseEntity<?> getInAceOrderOfRating() {
		List<Hospital> hospitals = hospitalService.getInAscOrderOfRating();
		if(!hospitals.isEmpty()) {
			return ResponseEntity.status(HttpStatus.FOUND)
					.header("info", "Data retrieved successfully! By Yugalk :)")
					.body(hospitals);
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found ! By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
		}
		
		
	}
	
	
	
			/***** DELETE MAPPING *****/
	
	//15. delete all hospital details 
	@DeleteMapping("/delete-all")
	public ResponseEntity<ApiResponse> deleteAll()
	{
		boolean status =hospitalService.deleteAll();
		if(status) {
		return ResponseEntity.status(HttpStatus.NO_CONTENT)
				.header("info", "data deleted successfully! By Yugalk :)")
				.build();
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found to delete! By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
		}
	}
	
	
	//16. delete hospital details by id
	@DeleteMapping("/deleteby-id/{id}")
	public ResponseEntity<ApiResponse> deleteById(@PathVariable("id") Long id)
	{
		boolean status= hospitalService.deleteById(id);
		if(status) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
					.header("info", "data deleted successfully! By Yugalk :)")
					.build();
		}
		else {
			ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found with id: " + id+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
		}
	}
	
	//17. delete hospital details by email
	@DeleteMapping("/deleteby-email/{email}")
	public ResponseEntity<ApiResponse> deleteByEmail(@PathVariable String email) {
	    boolean isDeleted = hospitalService.deleteByEmail(email);
	    if (isDeleted) {
	    	return ResponseEntity.status(HttpStatus.NO_CONTENT)
    				.header("info", "data deleted successfully! By Yugalk :)")
    				.build();
	    } else {
	    	ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found in email: " + email+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }
	}
		
	  	
	/*
	 //18. delete Delete by Rating Between max and min
	@DeleteMapping("/deleteby-rating/min/{minRating}/max/{maxRating}")
	public ResponseEntity<ApiResponse> deleteByRatingBetween(
							@PathVariable int minRating,
							@PathVariable int maxRating) {
		
		 boolean isDeleted = hospitalService.deleteByRatingBetween(minRating, maxRating);
		    if (isDeleted) {
		        return ResponseEntity.status(HttpStatus.NO_CONTENT)
		                .header("info", "Data deleted successfully! By Yugalk :)")
		                .build();
		    } else {
		        ApiResponse apiResponse = new ApiResponse();
		        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		        apiResponse.setMessage("No hospitals found with Min rating: " + minRating + " and Max rating: " + maxRating+" By Yugalk :(");
		        apiResponse.setTimeStamp(LocalDateTime.now());
		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
		                .header("info", "No data found! By Yugalk :(")
		                .body(apiResponse);
		    }	
	}
*/
	@DeleteMapping("/deletebyrating/{min}/{max}")
    public ResponseEntity<?> deleteHospitalByRatingRange(@PathVariable("min") double min, 
                                                         @PathVariable("max") double max) {

        int isDeleted = hospitalService.deleteHospitalsByRatingRange(min, max);

        if (isDeleted > 0) { 
            return ResponseEntity.ok()
                                 .header("info", "Hospitals deleted successfully by rating range!")
                                 .body("Hospitals with ratings between " + min + " and " + max + " have been deleted.");
        } else {
            // Create structured error response
            ApiResponse apiResponse = new ApiResponse();
            apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
            apiResponse.setMessage("No hospitals found with ratings between " + min + " and " + max + ".");
            apiResponse.setTimeStamp(LocalDateTime.now());
 
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .header("info", "Hospital deletion failed!")
                                 .body(apiResponse);
        }
    }
	
	//19. delete by rating rating greater than
	@DeleteMapping("/deleteby-rating-greater-than/{rating}")
	public ResponseEntity<ApiResponse> deleteByRatingGreaterThan(@PathVariable ("rating") int rating) {
		
		boolean isDelete = hospitalService.deleteByRatingGreaterThan(rating);
		
		if(isDelete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
	                .header("info", "Data deleted successfully! By Yugalk :)")
	                .build();
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found with rating greater than: " + rating+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }	
		
	}
	
	//20. Delete by rating greater than and location using JPQL
	@DeleteMapping("/hospitals/delete-by-rating-and-location/{rating}/{location}")
	public ResponseEntity<ApiResponse> deleteByRatingAndLocation(@PathVariable int rating, @PathVariable String location) {
		boolean isDelete = hospitalService.deleteByRatingAndLocation(rating, location );
		if(isDelete) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT)
	                .header("info", "Data deleted successfully! By Yugalk :)")
	                .build();
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospitals found with rating : " + rating+ " and location : "+location+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No data found! By Yugalk :(")
	                .body(apiResponse);
	    }	
		
	}

	/***** PUT MAPPING(update) *****/
	
	//21. update the entire hospital by id
	@PutMapping("/update-hospital/{id}")
	public ResponseEntity<?> updateHospitalById(@PathVariable("id") Long id, @RequestBody Hospital hospital) {
	    // Call the service method to update the hospital by ID
	    boolean isUpdated = hospitalService.updateHospitalById(id, hospital);
	    
	    if (isUpdated) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospital updated successfully! By Yugalk :)")
	                .body(hospitalService.getById(id));
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("Hospital not found with ID: " + id+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No hospital found to update! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	/***** PATCH MAPPING(Partially Update) *****/
	
	//22. partially update hospital name by id
	@PatchMapping("/update-name/{id}")
	public ResponseEntity<?> updateHospitalNameById(
				@PathVariable("id") Long id, 
				@RequestBody Map<String, String> updates) {
	     String newName = updates.get("name"); 
	     Optional<Hospital> hospital = hospitalService.getById(id);
	    boolean isUpdated = hospitalService.updateHospitalNameById(id, newName);

	    if (isUpdated) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospital name updated successfully! By Yugalk :)")
	                .body(hospital);
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("Hospital not found with ID: " + id +" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No hospital found to update! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	//23. update the new email based on current email using jpql query
	@PutMapping("/update-email/{currentEmail}")
	public ResponseEntity<ApiResponse> updateHospitalEmailByCurrentEmail(
			@PathVariable("currentEmail") String currentEmail, 
			@RequestBody Map<String, String> updates) {
	     
	    String newEmail = updates.get("email");
 
	    boolean isUpdated = hospitalService.updateHospitalEmailByCurrentEmail(currentEmail, newEmail);

	    if (isUpdated) {
	        return ResponseEntity.status(HttpStatus.OK)
	                .header("info", "Hospital email updated successfully!")
	                .body(new ApiResponse(HttpStatus.OK.value(), "Hospital email updated successfully! By Yugalk :)", LocalDateTime.now()));
	    } else {
	        ApiResponse apiResponse = new ApiResponse();
	        apiResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
	        apiResponse.setMessage("No hospital found with email: " + currentEmail+" By Yugalk :(");
	        apiResponse.setTimeStamp(LocalDateTime.now());
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	                .header("info", "No hospital found to update! By Yugalk :(")
	                .body(apiResponse);
	    }
	}

	
	//24. 
	/*
	//api to from postal to get information of village from the pincode
    @GetMapping("/pincodedetails/{pincode}")
    public ResponseEntity<String> getPinCodeDetails(@PathVariable("pincode") String pincode) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.postalpincode.in/pincode/" + pincode, 
                String.class
        );
        return ResponseEntity.status(HttpStatus.OK)
                .header("status", "DATA READ success by yugal.")
                .body(response.getBody());
    }
    
    //give the quotes, using the api from the dummyjson.com
    @GetMapping("/quotes/{quotes}")
    public ResponseEntity<String> getQuotes(@PathVariable("quotes") String quotes) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://dummyjson.com/quotes/" + quotes, 
                String.class
        );
        return ResponseEntity.status(HttpStatus.OK)
                .header("status", "DATA READ success by yugal.")
                .body(response.getBody());
    }
  
    //give the recipes, using the api from the dummyjson.com
    @GetMapping("/recipes/{recipes}")
    public ResponseEntity<String> getRecipes(@PathVariable("recipes") String recipes) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://dummyjson.com/recipes/" + recipes, 
                String.class
        );
        return ResponseEntity.status(HttpStatus.OK)
                .header("status", "DATA READ success by yugal.")
                .body(response.getBody());
    }
    
    
    ////WHEATHER API
    @GetMapping("/getweather")
    public ResponseEntity<String> getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(
                "https://api.openweathermap.org/data/2.5/weather?lat=21.152451&lon=79.080559&appid=f1d5ecbc4317fbad49cefd04b5848431", 
                String.class);
        return ResponseEntity.status(HttpStatus.OK)
                .header("status", "DATA READ success by Yugal.")
                .body(response.getBody());
    }
    

    */
}