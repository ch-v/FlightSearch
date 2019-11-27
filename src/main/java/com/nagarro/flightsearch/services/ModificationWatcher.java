package com.nagarro.flightsearch.services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import com.nagarro.flightsearch.controller.App;
import com.nagarro.flightsearch.utils.Constants;

/**
 * @author vishalchaudhary01
 *
 */
public class ModificationWatcher implements Runnable {
	HashMap<String, Long> lastModifiedAt = new HashMap<String, Long>();

	public void run() {
		File files[] = Constants.FILE.listFiles();
		ArrayList<String> l = new ArrayList<String>();
		for (File f : files) {
			if ((!(lastModifiedAt.containsKey(f.getName()))) || (f.lastModified() > lastModifiedAt.get(f.getName()))) {
				lastModifiedAt.put(f.getName(), f.lastModified());
				synchronized (App.flightsInfo) {
					App.flightsInfo.put(f.getName(), DirectoryReader.readFile(f));
				}
			}
			l.add(f.getName());
		}

		Set<String> fc = lastModifiedAt.keySet();
		Set<String> fname = new HashSet<String>();
		fname.addAll(fc);

		if (fc.size() == l.size())
			return;
		for (String string : fname) {
			if (!(l.contains(string))) {
				lastModifiedAt.remove(string);
				synchronized (App.flightsInfo) {
					App.flightsInfo.remove(string);
				}
			}
		}
	}
}
