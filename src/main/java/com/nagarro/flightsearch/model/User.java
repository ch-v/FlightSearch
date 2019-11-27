package com.nagarro.flightsearch.model;

import java.util.Date;

import com.nagarro.flightsearch.utils.Constants;

/**
 * @author vishalchaudhary01
 *
 */
public class User {

	String depLoc;
	String arrLoc;
	Date flightDate;
	String flightClass;
	int outputPreference;

	public User(String depLoc, String arrLoc, Date flightDate, String flightClass, int outputPreference) {
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		this.flightDate = flightDate;
		this.flightClass = flightClass;
		this.outputPreference = outputPreference;
	}

	public String getDepLoc() {
		return depLoc;
	}

	public String getArrLoc() {
		return arrLoc;
	}

	public Date getFlightDate() {
		return flightDate;
	}

	public String getFlightClass() {
		return flightClass;
	}

	public int getOutputPreference() {
		return outputPreference;
	}

	@Override
	public String toString() {
		return "depLoc=" + depLoc + ", arrLoc=" + arrLoc + ", flightDate=" + Constants.DATEFORMAT.format(flightDate)
				+ ", flightClass=" + flightClass + ", outputPreference=" + outputPreference;
	}
}
