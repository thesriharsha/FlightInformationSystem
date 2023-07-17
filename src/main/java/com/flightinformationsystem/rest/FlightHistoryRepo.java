package com.flightinformationsystem.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightinformationsystem.entities.FlightHistory;
import com.flightinformationsystem.entities.FlightId;

public interface FlightHistoryRepo extends JpaRepository<FlightHistory, FlightId> {

//	@Query("from FlightHistory where flight_no.flight_no =:flightno")
//	List<FlightHistory> getflighthistories(@Param("flightno") String flightno);
	
	//8th 
	@Query("  FROM FlightHistory fh INNER JOIN FETCH fh.flightNo f WHERE DATEDIFF(MINUTE, f.arrivalTime, fh.arrivalTime) = :minutes")
	List<FlightHistory> getDelayedFlights(@Param("minutes") int minutes);
	
	
}
 