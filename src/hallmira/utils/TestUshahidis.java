package hallmira.utils;

import java.util.Calendar;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;
import edu.grinnell.glimmer.ushahidi.UshahidiLocation;


/**
 * 
 * @author southpaw14 -- Andrew Kelley
 * @author m13hall -- Mira Hall
 * 
 * TestUshahidis is a test class that creates 
 * an UshahidiIncidentList
 *
 */
public class TestUshahidis {
	
	/**
	 * createIncidents creates an UshahidiIncidentList comprised of 12 different
	 * incidents.
	 * @return an UshahidiIncidentList of type UshahidiClient
	 */
	
	//This s the answer to part B
	
	public static UshahidiClient createIncidents(){
		UshahidiIncidentList list = new UshahidiIncidentList();
		/*
		 * We used only one location for simplicity and readability of our code.
		 * We could have created the different locations by copying and pasting
		 * and changing the following line 11 times, but that would have added
		 * little to what this list is used for.
		 */
		UshahidiLocation location3 = new UshahidiLocation(3, "house");
		//public UshahidiLocation(int id, java.lang.String name)
		//set(int year, int month, int date) 
		Calendar datez = Calendar.getInstance();
		datez.set(2000, 1, 1);
		list.addIncident(new UshahidiIncident(6, "F", datez, location3, "A"));
		
		Calendar datey = Calendar.getInstance();
		datey.set(2008, 6, 13);
		list.addIncident(new UshahidiIncident(10, "J", datey, location3, "A"));

		Calendar datex = Calendar.getInstance();
		datex.set(2010, 5, 20);
		list.addIncident( new UshahidiIncident(11, "K", datex, location3, "A"));

		Calendar datew = Calendar.getInstance();
		datew.set(1993, 12, 7);
		list.addIncident(new UshahidiIncident(3, "C", datew, location3, "A"));

		Calendar datev = Calendar.getInstance();
		datev.set(2006, 10, 7);
		list.addIncident(new UshahidiIncident(9, "I", datev, location3, "A"));
		
		Calendar dateu = Calendar.getInstance();
		dateu.set(1999, 5, 10);
		list.addIncident(new UshahidiIncident(5, "E", dateu, location3, "A"));

		Calendar datet = Calendar.getInstance();
		datet.set(1992, 6, 14);
		list.addIncident(new UshahidiIncident(2, "B", datet, location3, "A"));
		
		Calendar dates = Calendar.getInstance();
		dates.set(2012, 6, 22);
		list.addIncident( new UshahidiIncident(12, "L", dates, location3, "A"));
		
		/*
		 * We used the same date for incidents G and H to show that our algorithm
		 * for ordering by date works (see comments on specific issue of same dates
		 * in UshahidiExtensions.orderByDate.
		 */
		Calendar dater = Calendar.getInstance();
		dater.set(2001, 1, 1);
		list.addIncident(new UshahidiIncident(7, "G", dater, location3, "A"));

		Calendar dateq = Calendar.getInstance();
		dateq.set(2001, 1, 1);
		list.addIncident( new UshahidiIncident(8, "H", dateq, location3, "A"));

		Calendar datep = Calendar.getInstance();
		datep.set(1992, 3, 17);
		list.addIncident( new UshahidiIncident(1, "A", datep, location3, "A"));

		Calendar dateo = Calendar.getInstance();
		dateo.set(1994, 6, 13);
		list.addIncident( new UshahidiIncident(4, "D", dateo, location3, "A"));


/***
 * Note: when ordered, for ease of checking the correct order, the letters will
 * will be ordered A-L.
 ***/
		
		return list;
	}
}
