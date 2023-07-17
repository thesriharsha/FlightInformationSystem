package com.flightinformationsystem.entities;


import java.time.LocalTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="flights")
@Entity
public class Flight {
	
	@Id
	private String flightNo;
	@Column(name="durationinminutes")
	private int durationInMinutes;
	
	private LocalTime departureTime;
	private LocalTime arrivalTime;
	private String aircraft;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="fromcity", referencedColumnName = "code",insertable = false, updatable = false)
	@JsonIgnore
	private City fromCity;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="tocity",referencedColumnName = "code",insertable = false, updatable = false)
	@JsonIgnore
	private City toCity;
	
	@OneToMany(mappedBy = "flightNo", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<FlightHistory> flightHistories;
	
	
	public Flight() {
		super();
	}

	public Flight(String flight_no, int minutes, LocalTime departure_time, LocalTime arrival_time, String aircraft,
			City fcity, City tcity) {
		super();
		this.flightNo = flight_no;
		this.durationInMinutes = minutes;
		this.departureTime = departure_time;
		this.arrivalTime = arrival_time;
		this.aircraft = aircraft;
		this.fromCity = fcity;
		this.toCity = tcity;
	}

	public int getMinutes() {
		return durationInMinutes;
	}

	public void setMinutes(int minutes) {
		this.durationInMinutes = minutes;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightno) {
		this.flightNo = flightno;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departuretime) {
		this.departureTime = departuretime;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivaltime) {
		this.arrivalTime = arrivaltime;
	}

	public String getAircraft() {
		return aircraft;
	}

	public void setAircraft(String aircraft) {
		this.aircraft = aircraft;
	}

	public City getFromCity() {
		return fromCity;
	}

	public void setFromCity(City fromcity) {
		this.fromCity = fromcity;
	}

	public City getToCity() {
		return toCity;
	}

	public void setToCity(City tocity) {
		this.toCity = tocity;
	}

	public List<FlightHistory> getFlightHistories() {
		return flightHistories;
	}

	public void setFlightHistories(List<FlightHistory> flighthistories) {
		this.flightHistories = flighthistories;
	}

	
	
	
}
