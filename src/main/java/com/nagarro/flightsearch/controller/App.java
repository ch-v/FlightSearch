package com.nagarro.flightsearch.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.nagarro.flightsearch.model.Flight;
import com.nagarro.flightsearch.model.User;
import com.nagarro.flightsearch.services.InputAcceptor;
import com.nagarro.flightsearch.services.ModificationWatcher;

/**
 * @author vishalchaudhary01
 *
 */
public class App {

	public static HashMap<String, HashSet<Flight>> flightsInfo = new HashMap<String, HashSet<Flight>>();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		ArrayList<Flight> result = new ArrayList<Flight>();
		String choice = null;
		User uiObj;

		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		service.scheduleAtFixedRate(new ModificationWatcher(), 0, 3, TimeUnit.SECONDS);

		do {
			uiObj = InputAcceptor.enterInput();
			result.clear();
			synchronized (App.flightsInfo) {
				for (HashSet<Flight> set : flightsInfo.values()) {
					for (Flight f : set) {
						if (f.getDepLoc().equalsIgnoreCase(uiObj.getDepLoc())
								&& f.getArrLoc().equalsIgnoreCase(uiObj.getArrLoc())
								&& f.getFlightClass().equalsIgnoreCase(uiObj.getFlightClass())
								&& (uiObj.getFlightDate().compareTo(f.getValidTill()) <= 0) && f.isSeatAvailability())
							result.add(f);
					}
				}
			}
			if (uiObj.getOutputPreference() == 1)
				result.sort((Flight a, Flight b) -> a.getFare() - b.getFare());
			else
				Collections.sort(result, new Comparator<Flight>() {
					@Override
					public int compare(Flight a, Flight b) {
						Double x = a.getFlightDuration() - b.getFlightDuration();
						if (x < 0)
							return -1;
						else if (x > 0)
							return 1;
						else
							return 0;
					}
				});

			System.out.println("\nResult:");
			for (Flight f : result) {
				System.out.println(f);
			}
			System.out.print("\nWant to Exit (Enter Y/N)");
			while (!((choice = br.readLine()).equalsIgnoreCase("y") || choice.equalsIgnoreCase("n")))
				System.out.print("I could not Understand Enter Again:");
		} while (choice.equalsIgnoreCase("n"));
		service.shutdown();
	}
}
