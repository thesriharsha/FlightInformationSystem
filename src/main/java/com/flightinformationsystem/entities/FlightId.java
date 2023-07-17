package com.flightinformationsystem.entities;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class FlightId implements Serializable{

	@Column
	private String flightNo;
	@Column
	private LocalDate departureDate;
	public FlightId()
	{
		
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(departureDate, flightNo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FlightId other = (FlightId) obj;
		return Objects.equals(departureDate, other.departureDate) && Objects.equals(flightNo, other.flightNo);
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightno) {
		this.flightNo = flightno;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departuredate) {
		this.departureDate = departuredate;
	}
	
	
}
