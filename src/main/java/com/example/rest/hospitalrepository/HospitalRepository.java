package com.example.rest.hospitalrepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.rest.entity.Address;
import com.example.rest.entity.Hospital;

import jakarta.transaction.Transactional;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long>{

	public List<Hospital> findByName(String name);

	public Optional<List<Hospital>> findByRatingBetween(int minRating, int maxRating);

	public List<Hospital> findByAddress(Address address);
	
	public List<Hospital> findByRatingGreaterThan(int rating);

	public  List<Hospital> findByRatingLessThan(int rating);

	public List<Hospital> findByLocationAndName(String location, String name);

	public List<Hospital> findByLocationOrName(String location, String name);

	public List<Hospital> findByLocation(String location);
	
	public Optional<Hospital> findByEmail(String email);

	@Query("SELECT h FROM Hospital h WHERE h.location = :location AND h.rating BETWEEN :minRating AND :maxRating")
	public List<Hospital> findByLocationAndRatingBetween(
			@Param("location") String location, 
            @Param("minRating") int minRating, 
            @Param("maxRating") int maxRating);
	
	@Query(value="SELECT * from Hospital ORDER BY rating ASC ", nativeQuery = true)
	public List<Hospital> findByAscOrderOfRating();
	
	@Modifying
	@Transactional
//	public int deleteByRatingBetween(int minRating, int maxRating);
	int deleteByRatingBetween(double minRating, double maxRating);
	
	
	@Modifying
	@Transactional
	public int deleteByRatingGreaterThan(int rating);
	
	@Modifying
	@Transactional
	public int deleteByRatingAndLocation(int rating, String location);	
	
	@Modifying
	@Transactional
	public Optional<Hospital> deleteByEmail(String email);

	@Modifying
	@Transactional
	@Query("UPDATE Hospital h SET h.email = :newEmail WHERE h.email = :currentEmail")
	public int updateEmailByCurrentEmail(@Param("currentEmail") String currentEmail, @Param("newEmail") String newEmail);
	
	boolean existsByEmail(String email); 


	
	 
	

}
