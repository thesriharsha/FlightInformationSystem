package com.flightinformationsystem.entities;
import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="scheduledflights")

public class ScheduledFlight {

	
	private LocalTime departureTime;
	private LocalDate arrivalDate;
	private LocalTime arrivalTime;
	@Column(name="durationinminutes")
	private int durationInMinutes;
	@EmbeddedId
	private FlightId flightPk;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="fromCity",referencedColumnName = "code")
	
	private City fromCity;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="toCity",referencedColumnName = "code")
	private City toCity;
	

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="flightNo",insertable = false, updatable = false)
	private Flight flightNo;

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

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int minutes) {
		this.durationInMinutes = minutes;
	}

	public LocalTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalTime departuretime) {
		this.departureTime = departuretime;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivaldate) {
		this.arrivalDate = arrivaldate;
	}

	public LocalTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalTime arrivaltime) {
		this.arrivalTime = arrivaltime;
	}

	public FlightId getFlightPk() {
		return flightPk;
	}

	public void setFlightPk(FlightId flightpk) {
		this.flightPk = flightpk;
	}

	public Flight getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(Flight flightno) {
		this.flightNo = flightno;
	}

	
	
}
