package com.flightinformationsystem.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightinformationsystem.entities.Flight;

public interface FlightRepo extends JpaRepository<Flight, String> {
	
	@Query("from Flight where fromCity.code=:fcity and toCity.code=:tcity")
	List<Flight> getFlightsByRoute(@Param("fcity") String fcity, @Param("tcity") String tcity);
	
	//4th
	
	
	
	//3rd
//	@Query(value = "SELECT from Flights\n"
//			+ "where From_City=:fcity and To_City =:tcity", nativeQuery = true)
//	List<Flight> getFlightsByRoute(@Param("fcity") String fcity, @Param("tcity") String tcity);

}
