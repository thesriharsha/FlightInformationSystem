package com.flightinformationsystem.rest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.flightinformationsystem.entities.City;
import com.flightinformationsystem.entities.Flight;
import com.flightinformationsystem.entities.FlightHistory;
import com.flightinformationsystem.entities.FlightId;
import com.flightinformationsystem.entities.ScheduledFlight;

@RestController
public class Controller {

	@Autowired
	private CityRepo cr;
	@Autowired
	private FlightRepo fr;
	@Autowired
	private ScheduledFlightRepo sr;
	@Autowired
	private FlightHistoryRepo fhr;
	//1st List of Cities 
	@GetMapping("/allcities")
	public List<String> getCities()
	{
		List<String> cities = new ArrayList<String>();
		for(City c : cr.findAll())
		{
			cities.add(c.getName());
		}
		return cities;
	}
	@GetMapping("/cityall")
	public List<String> getCitiess()
	{
		return cr.getCityNames();
	}
	//2nd - List Flights by Pagination
	@GetMapping("/flightsbypage/{pageno}")
	public List<Flight> getflightpages(@RequestParam() Integer pageno)
	{
		var result = fr.findAll(PageRequest.of(pageno, 5 ));
		return result.getContent();
	}
	
	
	
	@GetMapping("/allflights")
	public List<Flight> getallflights()
	{
		return fr.findAll();
	}
	//3rd - Flight From city to To city
	@GetMapping("/flightsbyroute/{fcity}/{tcity}")
	public List<Flight> displayFlightsByRoute(@PathVariable("fcity") String fcity, @PathVariable("tcity") String tcity)
	{
		return fr.getFlightsByRoute(fcity, tcity);
	}
	
	//4th - FlightHistory of a flight
	@GetMapping("/flighthistory/{flightno}")
	public List<FlightHistory> displayflighthistories(@PathVariable("flightno") String flightno)
	{
		return fr.findById(flightno).get().getFlightHistories();
	}
	
	//5th - Add Scheduled Flight with From and To dates
	@PostMapping("/addScflight")
	public ScheduledFlight addScheduledFlight(@RequestParam() String flightno, @RequestParam() LocalDate fromdate, @RequestParam LocalDate todate)
	{
		var flight = fr.findById(flightno);
		if(flight.isPresent())
		{
			if(sr.getbyflightno(flightno,fromdate)==null)
			{
				ScheduledFlight sf = new ScheduledFlight();
				FlightId fpk = new FlightId();
				fpk.setFlightNo(flightno);
				fpk.setDepartureDate(fromdate);
				sf.setFlightPk(fpk);
				sf.setArrivalDate(todate);
				sf.setFromCity(flight.get().getFromCity());
				sf.setToCity(flight.get().getToCity());
				sf.setDepartureTime(flight.get().getDepartureTime());
				sf.setArrivalTime(flight.get().getArrivalTime());
				sf.setDurationInMinutes(flight.get().getMinutes());
				
				sr.save(sf);
				return sf;	
			}
			else
				throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED,"Flight with this Departure Date is scheduled already");
		}
		else
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Flight not found");
	}
	
	//6th - Adding a new Flight
	@PostMapping("/addflight")
	public Flight addflight(@RequestParam() String flight_no,@RequestParam() String from_city, @RequestParam() String to_city, @RequestParam() Integer minutes, 
			@RequestParam() LocalTime departure_time,@RequestParam() LocalTime arrival_time, @RequestParam() String aircraft)
	{
		if(fr.findById(flight_no).isPresent())
			throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
		else {
			var fromCity=cr.findById(from_city);
			var toCity=cr.findById(to_city);
			if(fromCity.isPresent() && toCity.isPresent())
			{
				Flight flight = new Flight( flight_no,  minutes,  departure_time, arrival_time,  aircraft,
						fromCity.get(),toCity.get());
				
				fr.save(flight);
				return flight;
			}
			else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND,"City code not found");
			}
		}
	}
	
	//7th - Cancel Scheduled Flights
	@DeleteMapping("/cancelflights")
	public void cancelScFlights(@RequestParam() LocalDate fromdate, @RequestParam() LocalDate todate)
	{
		sr.cancelflights(fromdate, todate);
	}
	
	//7th-v2 Cancel Scheduled Flights
	@DeleteMapping("/cancelflight")
	public void cancelFlights(@RequestParam() LocalDate fromdate, @RequestParam() LocalDate todate)
	{
		var scFlights = sr.getflights(fromdate, todate);
		if(scFlights.size()==0)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No Flights Scheduled in the given dates");
		else {
			for(var scf: scFlights)
			{
				sr.delete(scf);
			}
		}
	}
	//8th Getting all Delayed Flights
	@GetMapping("/delayedflights")
	public List<FlightHistory> delayedFlights(@RequestParam() int minutes)
	{
		return fhr.getDelayedFlights(minutes);
	}
	
	//10th Get Scheduled Flights by from city and departure date
	@GetMapping("/scheduledflights")
	public List<ScheduledFlight> scflightsbydate(@RequestParam() String fromcity, @RequestParam() LocalDate departuredate)
	{
		return sr.getScFlights(fromcity,departuredate);
	}
	
	
	// 9th - Add City
	@PostMapping("/addcity")
	public City addcity(@RequestParam() String code, @RequestParam() String name, @RequestParam() Integer minutes, @RequestParam() String country)
	{
		City city = new City(code, name, minutes, country);
		cr.save(city);
		return city;
	}
	
	@PostMapping("cityadd")
	public City cityadd(@org.springframework.web.bind.annotation.RequestBody City city)
	{
		cr.save(city);
		return city;
	}
	//9TH - Deleting City
	@DeleteMapping("/deletecity")
	public String deletecity(@RequestParam() String code)
	{
		if(cr.findById(code).isPresent())
		{
			cr.deleteById(code);
			return "City Deleted Successfully";
		}
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No City found with the given Code");
	}
	
	//9th Updating a City
	@PutMapping("/updateminutes")
	public City updateCity(@RequestParam() String code, @RequestParam() int minutes)
	{
		var c = cr.findById(code);
		if(c.isPresent())
		{
			var city = c.get();
			city.setMinutes(minutes);
			cr.save(city);
			return city;
		}
		else 
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No City found with the given Code");
	}
	
	
	
}
