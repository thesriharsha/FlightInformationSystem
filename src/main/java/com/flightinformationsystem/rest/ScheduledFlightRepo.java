package com.flightinformationsystem.rest;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.flightinformationsystem.entities.FlightId;
import com.flightinformationsystem.entities.ScheduledFlight;

import jakarta.transaction.Transactional;

public interface ScheduledFlightRepo extends JpaRepository<ScheduledFlight, FlightId> {

	//Query Derivation
	@Query("from ScheduledFlight where flightPk.departureDate=:departuredate and flightPk.flightNo=:flightno")
	public ScheduledFlight getbyflightno(@Param("flightno") String flightno,@Param("departuredate") LocalDate departuredate);
	
	@Modifying
	@Transactional
	@Query("Delete from ScheduledFlight where flightPk.departureDate>=:fromdate and flightPk.departureDate<=:todate")
	public void cancelflights(@Param("fromdate") LocalDate fromdate, @Param("todate") LocalDate todate);
	
	
	@Query("from ScheduledFlight where flightPk.departureDate>=:fromdate and flightPk.departureDate<=:todate")
	public List<ScheduledFlight> getflights(@Param("fromdate") LocalDate fromdate, @Param("todate") LocalDate todate);
	
	//10th 
	@Query("from ScheduledFlight where flightPk.departureDate=:departuredate and fromCity.code=:fromcity")
	public List<ScheduledFlight> getScFlights(@Param("fromcity") String fromcity,@Param("departuredate") LocalDate departuredate );
	
}
