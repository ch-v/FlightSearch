package com.nagarro.flightsearch.model;

import java.util.Date;

import com.nagarro.flightsearch.utils.Constants;

/**
 * @author vishalchaudhary01
 *
 */
public class Flight {

	String flightNo;
	String depLoc;
	String arrLoc;
	Date validTill;
	String flightTime;
	Double flightDuration;
	int fare;
	boolean seatAvailability;
	String flightClass;

	public Flight(String flightNo, String depLoc, String arrLoc, int fare, Date validTill, String flightTime,
			Double flightDuration, boolean seatAvailability, String flightClass) {

		this.flightNo = flightNo;
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.validTill = validTill;
		this.flightTime = flightTime;
		this.flightDuration = flightDuration;
		this.seatAvailability = seatAvailability;
		this.flightClass = flightClass;
		if (flightClass.equalsIgnoreCase("EB"))
			fare = 140 * fare / 100;
		this.fare = fare;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public String getDepLoc() {
		return depLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public int getFare() {
		return fare;
	}

	public Date getValidTill() {
		return validTill;
	}

	public String getFlightTime() {
		return flightTime;
	}

	public Double getFlightDuration() {
		return flightDuration;
	}

	public boolean isSeatAvailability() {
		return seatAvailability;
	}

	public String getFlightClass() {
		return flightClass;
	}

	@Override
	public String toString() {
		return "flightNo=" + flightNo + ", depLoc=" + depLoc + ", arrLoc=" + arrLoc + ", validTill="
				+ Constants.DATEFORMAT.format(validTill) + ", flightTime=" + flightTime + ", flightDuration="
				+ String.format("%.2f", flightDuration) + ", fare=" + fare + ", seatAvailability=" + seatAvailability
				+ ", flightClass=" + flightClass;
	}
}
