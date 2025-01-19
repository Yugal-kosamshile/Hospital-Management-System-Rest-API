package com.example.rest.hospitalservice;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest.entity.Address;
import com.example.rest.entity.Hospital;
import com.example.rest.hospitalrepository.HospitalRepository;

@Service
public class HospitalService {

	@Autowired
	HospitalRepository hospitalRepository;
	
	/***** POST MAPPING (SAVE) *****/
	public Hospital saveHospitalDetail(Hospital hospital) {
		
		return hospitalRepository.save(hospital);
	}
	
	public List<Hospital> saveBulkHospital(List<Hospital> hospital) {
		return hospitalRepository.saveAll(hospital);
	}
	
	/***** GET MAPPING (RETRIEVE) *****/
	public List<Hospital> getAllHospital() {
		return hospitalRepository.findAll();
	}
	public Optional<Hospital> getById(Long id) {
		return hospitalRepository.findById(id);
	}
	public List<Hospital> getByName(String name) {
		return hospitalRepository.findByName(name);
	}
	
	public List<Hospital> getByLocation(String location) {
		return  hospitalRepository.findByLocation(location);
	}
	public List<Hospital> getByAddress(Address address) {
		return hospitalRepository.findByAddress(address);
	}
	public Optional<List<Hospital>> getByRating(int minRating, int maxRating) {
		return hospitalRepository.findByRatingBetween(minRating, maxRating);
	}

	 // Retrieve hospitals with a rating greater than a specified value
    public List<Hospital> findHospitalsWithRatingGreaterThan(int rating) {
        return hospitalRepository.findByRatingGreaterThan(rating);
    }

    // Retrieve hospitals with a rating less than a specified value
    public List<Hospital> findHospitalsWithRatingLessThan(int rating) {
        return hospitalRepository.findByRatingLessThan(rating);
    }

    // Find hospitals by Location AND Name (Both conditions must be true)
    public List<Hospital> findHospitalsByLocationAndName(String location, String name) {
        return hospitalRepository.findByLocationAndName(location, name);
    }

    // Find hospitals by Location OR Name (At least one condition must be true)
    public List<Hospital> findHospitalsByLocationOrName(String location, String name) {
        return hospitalRepository.findByLocationOrName(location, name);
    }
    
    public List<Hospital> getbyLocationAndRatingBetween(String location, int minRating, int maxRating) {
		return hospitalRepository.findByLocationAndRatingBetween(location, minRating, maxRating);
	}
    public List<Hospital> getInAscOrderOfRating() {
		return hospitalRepository.findByAscOrderOfRating();
	}

	/***** DELETE MAPPING (DELETE) *****/
	public boolean deleteById(Long id) {
		if(hospitalRepository.existsById(id))
		{	hospitalRepository.deleteById(id);
			return true;
		}
		return false;
	}

	public boolean deleteAll() {
		 hospitalRepository.deleteAll();
		return false;
	}

	public boolean deleteByEmail(String email) {
	    Optional<Hospital> hospital = hospitalRepository.deleteByEmail(email);
	    if (hospital.isPresent()) {
	        hospitalRepository.delete(hospital.get());
	        return true;
	    }
	    return false;
	}

//	public boolean deleteByRatingBetween(int minRating, int maxRating) {
//	    int deletedCount = hospitalRepository.deleteByRatingBetween(minRating, maxRating);
//	    return deletedCount > 0;  
//	}
	public int deleteHospitalsByRatingRange(double minRating, double maxRating) { 
	    return hospitalRepository.deleteByRatingBetween(minRating, maxRating);
	}

	public boolean deleteByRatingGreaterThan(int rating) {
	    int deletedCount = hospitalRepository.deleteByRatingGreaterThan(rating);
	    return deletedCount > 0;  
	}

	public boolean deleteByRatingAndLocation(int rating, String location) {
		int deletedCount = hospitalRepository.deleteByRatingAndLocation(rating, location);
		return deletedCount > 0;
	}

	public boolean updateHospitalById(Long id, Hospital hospital) {
	    Optional<Hospital> existingHospital = hospitalRepository.findById(id);
	    
	    if (existingHospital.isPresent()) {
	        Hospital updatedHospital = existingHospital.get();

	        updatedHospital.setName(hospital.getName());
	        updatedHospital.setLocation(hospital.getLocation());
	        updatedHospital.setRating(hospital.getRating());
	        updatedHospital.setEmail(hospital.getEmail());
	        updatedHospital.setAddress(hospital.getAddress());
	        
	        hospitalRepository.save(updatedHospital);
	        return true;
	    } else {
	        return false;
	    }
	}

	public boolean updateHospitalNameById(Long id, String newName) {
	    Optional<Hospital> existingHospital = hospitalRepository.findById(id);

	    if (existingHospital.isPresent()) {
	        Hospital hospital = existingHospital.get();
	        hospital.setName(newName);
	        hospitalRepository.save(hospital);
	        return true;
	    } else {
	        return false;
	    }
	}

	public boolean updateHospitalEmailByCurrentEmail(String currentEmail, String newEmail) {
	    int updatedCount = hospitalRepository.updateEmailByCurrentEmail(currentEmail, newEmail);
	    return updatedCount > 0;
	}


 

}
