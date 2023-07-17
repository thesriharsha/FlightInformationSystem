package com.flightinformationsystem.rest;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightinformationsystem.entities.City;

public interface CityRepo extends JpaRepository<City, String>{

	@Query("select name from City ")
	public List<String> getCityNames();
	
	//Query Derivation
	//public void deleteByCode(String code);
}
