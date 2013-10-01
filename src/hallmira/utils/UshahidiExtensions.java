package hallmira.utils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;
import edu.grinnell.glimmer.ushahidi.UshahidiLocation;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;


public class UshahidiExtensions {

	public static void printIncident(PrintWriter pen, UshahidiIncident inc){
		pen.println("Incident #: "+ inc.getTitle());
		/*	pen.println("DESCRIPTION");
	pen.println("Location: "+ inc.getLocation());
	pen.println("Status: (" + inc.getMode()+ ", "+ inc.getActive() + 
			", " + inc.getVerified() + ")");
		 */
	}
	public static UshahidiIncidentList incidentList(){
		String[] names = {"muffin", "blueberry", "pancake", "waffle", 
				"hash brown", "bacon", "sausage", "eggs", 
				"yogurt", "orange", "strawberries", "pizza"};
		String[] descriptions = {"good", "bad", "awesomesauce", "mindblowing",
				"belated", "cold", "warm", "tasty", "awkward",
				"healthy", "delicious", "vitamin-rich", "greasy"};
		String[] locations = {"house", "apartment", "park", "America",
				"grocery store", "coffee shop", "GRINNELL", "Iowa",
				"farm", "Florida", "field", "Paglioni's"};


		UshahidiIncidentList list = new UshahidiIncidentList();
		for (int i = 0; i < 12; i++){
			Calendar date = Calendar.getInstance();
			date.set(i, i, i, i, i, i);
			UshahidiLocation location = new UshahidiLocation(i, locations[i]);
			list.addIncident(new UshahidiIncident(i, names[i], date, location,
					descriptions[i]));

		}
		return list;
	}

	public static void printList(PrintWriter pen, ArrayList list) {
		for(int i = 0; i < list.size(); i++){
			printIncident(pen, (UshahidiIncident)list.get(i));
		}
	}
	//public static int getAttributeToCompare(UshahidiIncident inc, String attribute){
	//	if (attribute.compareTo("ID") == 0){
	//		return inc.getId();
	//	}
	//	if(attribute.compareTo("Date") == 0){
	//		///do some parsing, put in terms of days
	//		return inc.getMode(); //change!!!
	//	}else
	//		return -1;
	//}

	public static ArrayList orderByDate(UshahidiIncidentList list)throws Exception{
		//initialze list
		ArrayList sortedList = new ArrayList<UshahidiIncident>();

		//put first element UshahidiIncidentList
		if(list.hasMoreIncidents()){
			sortedList.add(list.nextIncident());
		}else 
			return sortedList;

		//for each element in ushahidiIncidentList\
		while(list.hasMoreIncidents()){
			UshahidiIncident next = list.nextIncident();
			for(int i = 0; i < sortedList.size(); i++){
				int j = ((UshahidiIncident)sortedList.get(i)).getDate().compareTo(next.getDate());
				if (j > 0){ //if next has an earlier date than
					sortedList.add(i, next);
					break;
				}
				else if (j == 0) {
					if(((UshahidiIncident)sortedList.get(i)).getId() > next.getId()){
						sortedList.add(i, next);
						break;
					}else {
						sortedList.add(i + 1, next);
						break;
					}
				} else if (i == sortedList.size() - 1) {
					sortedList.add(next);
					break;
				}
			}//for	
			continue;
		}//while

		return sortedList;

	}

	public static ArrayList orderById(UshahidiIncidentList list)throws Exception{
		//initialze list
		ArrayList sortedList = new ArrayList<UshahidiIncident>();

		//put first element UshahidiIncidentList
		if(list.hasMoreIncidents()){
			sortedList.add(list.nextIncident());
		}else 
			return sortedList;

		//for each element in ushahidiIncidentList\
		while(list.hasMoreIncidents()){
			UshahidiIncident next = list.nextIncident();
			for(int i = 0; i < sortedList.size(); i++){
				if (((UshahidiIncident)sortedList.get(i)).getId() > (next.getId())){ //if next smaller Id than this item in sorted list
					sortedList.add(i, next);
					break;
				} else if (i == sortedList.size() - 1) {
					sortedList.add(next);
					break;
				} else if (((UshahidiIncident)sortedList.get(i)).getId() == (next.getId())) {
					throw new Exception("Two Id's should not be the same!");
				}
			}//for	
			continue;
		}//while

		return sortedList;

	}

	public static void getExtremes(PrintWriter pen, UshahidiIncidentList list) throws Exception{
		ArrayList sorted = orderById(list);
		pen.println("Smallest Id: ");
		printIncident(pen, (UshahidiIncident)sorted.get(0));
		pen.println("Greatest Id: ");
		printIncident(pen, (UshahidiIncident)sorted.get(sorted.size() - 1));

	}

	public static UshahidiIncident[] identifyArray(UshahidiIncidentList list, Calendar startDate, Calendar endDate) throws Exception{
		ArrayList sorted = orderById(list);
		int i = 0;
		int startIndex = -1;
		int endIndex = -1;
			while(startIndex == -1 && i < sorted.size()){
				if(((UshahidiIncident)sorted.get(i)).getDate().compareTo(startDate) >= 0){
					startIndex = i;
				}else {
					i++;
				}
			}//while
			while(endIndex == -1 && i < sorted.size()){
				if(((UshahidiIncident)sorted.get(i)).getDate().compareTo(endDate) >= 0){
					endIndex = i-1;
				}else {
					i++;
				}
			}//while


		if (startIndex == -1){
			throw new Exception("No incidents between startDate and endDate");
		}else if (endIndex == -1){
			endIndex = sorted.size() - 1;
		}
		UshahidiIncident[] returnArray = new UshahidiIncident[endIndex-startIndex + 1];
			return (UshahidiIncident[]) sorted.subList(startIndex, endIndex + 1).toArray(returnArray);
	}
	public static void identify(PrintWriter pen, UshahidiIncidentList list, Calendar startDate, Calendar endDate) throws Exception{
		UshahidiIncident[] relevant = identifyArray(list, startDate, endDate);
		for (int i = 0; i < relevant.length; i++){
			printIncident(pen, relevant[i]);
		}
	}
	}

