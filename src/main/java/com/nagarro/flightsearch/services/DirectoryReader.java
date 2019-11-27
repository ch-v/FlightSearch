package com.nagarro.flightsearch.services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Date;
import java.util.HashSet;
import java.util.StringTokenizer;

import com.nagarro.flightsearch.controller.App;
import com.nagarro.flightsearch.model.Flight;
import com.nagarro.flightsearch.utils.Constants;

/**
 * @author vishalchaudhary01
 *
 */
public class DirectoryReader {
	public static void readDir() {
		int i;
		HashSet<Flight> flight_Set;
		File files[] = Constants.FILE.listFiles();
		for (i = 0; i < files.length; i++) {
			System.out.println("Reading File : " + files[i]);
			flight_Set = readFile(files[i]);
			synchronized (App.flightsInfo) {
				App.flightsInfo.put(files[i].getName(), flight_Set);
			}
		}
		System.out.println("Read All CSV Files");
	}

	public static HashSet<Flight> readFile(File file) {
		BufferedReader reader = null;
		HashSet<Flight> flight_Set = new HashSet<Flight>();
		try {
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			line = reader.readLine();

			while (line != null) {
				Flight f = manipulateLine(line);
				line = reader.readLine();
				flight_Set.add(f);
			}
		} catch (Exception e) {
			System.err.println("Could Not Read the File");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (Exception e) {
					System.err.println("Could Not Close the File");
				}
			}
		}
		return flight_Set;
	}

	private static Flight manipulateLine(String line) {

		StringTokenizer st = new StringTokenizer(line, "|");

		String flightNo = st.nextToken();
		String depLoc = st.nextToken();
		String arrLoc = st.nextToken();

		String validTillDate = st.nextToken();
		Date validTill = new Date();
		try {
			validTill = Constants.DATEFORMAT.parse(validTillDate);
		} catch (ParseException e) {
			System.err.print("Date not in appropriate(dd-MM-yyyy) format");
		}

		String flightTime = st.nextToken();
		Double flightDuration = Double.parseDouble(st.nextToken());
		int fare = Integer.parseInt(st.nextToken());

		String avail = st.nextToken();
		Boolean seatAvailability;
		if (avail.charAt(0) == 'Y')
			seatAvailability = true;
		else
			seatAvailability = false;

		String flightClass = st.nextToken();

		return new Flight(flightNo, depLoc, arrLoc, fare, validTill, flightTime, flightDuration, seatAvailability,
				flightClass);
		// System.out.println(f);
		// Utility.flightSet.add(f);
	}
}
