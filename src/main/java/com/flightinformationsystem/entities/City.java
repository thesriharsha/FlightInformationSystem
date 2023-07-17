package com.flightinformationsystem.entities;

import java.util.List;

import org.hibernate.proxy.HibernateProxy;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name="cities")
public class City {

	@Id
	private String code;
	private String name;
	@Column(name="minutesfromutc")
	private int minutes;
	private String country;

	
	
	public City() {
		super();
	}

	public City(String code, String name, int minutes, String country) {
		super();
		this.code = code;
		this.name = name;
		this.minutes = minutes;
		this.country = country;
	}

	@OneToMany(mappedBy = "fromCity")
	@JsonIgnore
	private List<Flight> fromCities;
	
	@OneToMany(mappedBy = "toCity")
	@JsonIgnore
	private List<Flight> toCities;
	
//	@Transient
//	@JsonIgnore
//	private HibernateProxy hibernateLazyInitializer;
	
	@OneToMany(mappedBy = "fromCity")
	@JsonIgnore
	private List<ScheduledFlight> sfromcities;
	
	@OneToMany(mappedBy = "toCity")
	@JsonIgnore
	private List<ScheduledFlight> stocities;
	
	@OneToMany(mappedBy = "fromCity")
	@JsonIgnore
	private List<FlightHistory>hfromcities;
	
	@OneToMany(mappedBy = "toCity")
	@JsonIgnore
	private List<FlightHistory> htocities;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public List<Flight> getFromcities() {
		return fromCities;
	}

	public void setFromcities(List<Flight> fromcities) {
		this.fromCities = fromcities;
	}

	public List<Flight> getTocities() {
		return toCities;
	}

	public void setTocities(List<Flight> tocities) {
		this.toCities = tocities;
	}

	public List<ScheduledFlight> getSfromcities() {
		return sfromcities;
	}

	public void setSfromcities(List<ScheduledFlight> sfromcities) {
		this.sfromcities = sfromcities;
	}

	public List<ScheduledFlight> getStocities() {
		return stocities;
	}

	public void setStocities(List<ScheduledFlight> stocities) {
		this.stocities = stocities;
	}

	public List<FlightHistory> getHfromcities() {
		return hfromcities;
	}

	public void setHfromcities(List<FlightHistory> hfromcities) {
		this.hfromcities = hfromcities;
	}

	public List<FlightHistory> getHtocities() {
		return htocities;
	}

	public void setHtocities(List<FlightHistory> htocities) {
		this.htocities = htocities;
	}


	
	
	
}
