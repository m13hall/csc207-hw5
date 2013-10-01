package hallmira.utils;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;

/**
 * 
 * @author southpaw14 -- Andrew Kelley
 * @author m13hall -- Mira Hall
 * 
 * This class is an extension for Ushahidi that includes
 * methods to manipulate UshahidiIncidentList[s].
 *
 */
public class UshahidiExtensions {

	/**
	 * printIncident prints out a given incident.
	 * @param pen, a pen to write out the incident
	 * @param inc, the incident to write
	 * 
	 * NOTE: For purely reading the results of the list tests, 
	 * the boolean printFlag set equal to false will allow for 
	 * less clutter when reading the output as it only shows the
	 * title.
	 * If you want the whole incident to be printed, change
	 * printFlag to true.
	 */
	public static void printIncident(PrintWriter pen, UshahidiIncident inc){
		//This flag prevents some everything but the title from being printed
		boolean printFlag = false;
		pen.println("Incident #: "+ inc.getTitle());
		if (printFlag) {
			pen.println("DESCRIPTION");
			pen.println("Location: "+ inc.getLocation());
			pen.println("Status: (" + inc.getMode()+ ", "+ inc.getActive() + 
					", " + inc.getVerified() + ")");
		}	 
	}

	/**
	 * printList takes prints out an ArrayList of UshahidiIncidents.
	 * @param pen, a PrintWriter
	 * @param list, an UshahidiIncident list
	 */
	public static void printList(PrintWriter pen, ArrayList<UshahidiIncident> list) {
		for(int i = 0; i < list.size(); i++){
			printIncident(pen, list.get(i));
		}
	}

	/**
	 * printList takes prints out a UshahidiClient list of UshahidiIncidents
	 * @param pen, a PrintWriter
	 * @param list, an UshahidiClient
	 * @throws Exception
	 */
	public static void printList(PrintWriter pen, UshahidiClient list) throws Exception{
		while(list.hasMoreIncidents()) {
			printIncident(pen, list.nextIncident());
		}
	}

	/**
	 * orderByDate takes a UshahidiClient and returns an ArrayList of UshahidiIncidents
	 * in order by their date
	 * @param list, an UshahidiClient
	 * @return an ArrayList of UshahidiIncidents in order of date
	 * @throws Exception
	 */
	public static ArrayList<UshahidiIncident> orderByDate(UshahidiClient list)throws Exception{
		//initialize list
		ArrayList<UshahidiIncident> sortedList = new ArrayList<UshahidiIncident>();

		//put first element UshahidiIncidentList
		if(list.hasMoreIncidents()){
			sortedList.add(list.nextIncident());
		}else 
			return sortedList;

		//for each element in ushahidiIncidentList\
		while(list.hasMoreIncidents()){
			UshahidiIncident next = list.nextIncident();
			for(int i = 0; i < sortedList.size(); i++){
				int j = sortedList.get(i).getDate().compareTo(next.getDate());
				if (j > 0){ //if next has an earlier date than
					sortedList.add(i, next);
					break;
				}
				//If dates are the same, orders by lowest to highest Id number.
				else if (j == 0) {
					if(sortedList.get(i).getId() > next.getId()){
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

	/**
	 * orderById takes a UshahidiClient and returns an ArrayList of UshahidiIncidents
	 * in order by their Id number
	 * @param list, an UshahidiClient
	 * @return an ArrayList of UshahidiIncidents in order of lowest to highest Id.
	 * @throws Exception
	 * 
	 * NOTE: we are under the assumption that no to incidents may have the same Id number
	 */
	public static ArrayList <UshahidiIncident> orderById(UshahidiClient list)throws Exception{
		//initialize list
		ArrayList<UshahidiIncident> sortedList = new ArrayList<UshahidiIncident>();

		//put first element UshahidiIncidentList
		if(list.hasMoreIncidents()){
			sortedList.add(list.nextIncident());
		}else 
			return sortedList;

		//for each element in ushahidiIncidentList\
		while(list.hasMoreIncidents()){
			UshahidiIncident next = list.nextIncident();
			for(int i = 0; i < sortedList.size(); i++){
				if (sortedList.get(i).getId() > (next.getId())){ //if next smaller Id than this item in sorted list
					sortedList.add(i, next);
					break;
				} else if (i == sortedList.size() - 1) {
					sortedList.add(next);
					break;
				} else if (sortedList.get(i).getId() == (next.getId())) {
					throw new Exception("Two Id's should not be the same!");
				}
			}//for	
			continue;
		}//while

		return sortedList;

	}

	/**
	 * getExtremes prints the incidents, from a list, with the smallest 
	 * and largest Id number.
	 * @param pen, a PrintWriter
	 * @param list, an UshahidiClient
	 * @throws Exception
	 */
	public static void getExtremes(PrintWriter pen, UshahidiClient list) throws Exception{
		ArrayList<UshahidiIncident> sorted = orderById(list);
		pen.println("Smallest Id: ");
		printIncident(pen, (UshahidiIncident)sorted.get(0));
		pen.println("Greatest Id: ");
		printIncident(pen, (UshahidiIncident)sorted.get(sorted.size() - 1));

	}

	/**
	 * identifyArray creates a sorted ArrayList of UshahidiIncidents
	 * and then creates an UshahidiIncident[] (array) of the elements of the
	 * original list between the start date and end date(inclusive)
	 * @param list, an UshahidiClient
	 * @param startDate, a Calendar date
	 * @param endDate, a Calendar date
	 * @return an array that contains incidents occurring between
	 * start date and end date.
	 * @throws Exception
	 */
	public static UshahidiIncident[] identifyArray(UshahidiClient list, Calendar startDate, Calendar endDate) throws Exception{
		ArrayList<UshahidiIncident> sorted = orderById(list);
		int i = 0;
		int startIndex = -1;
		int endIndex = -1;
		//marks the index of the first incident with date later than startDate
		while(startIndex == -1 && i < sorted.size()){
			if(sorted.get(i).getDate().compareTo(startDate) >= 0){
				startIndex = i;
			}else {
				i++;
			}
		}//while
		//marks the index of the last incident with date earlier than endDate 
		while(endIndex == -1 && i < sorted.size()){
			if(sorted.get(i).getDate().compareTo(endDate) >= 0){
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
		//Initializes UshahidiIncident array
		UshahidiIncident[] returnArray = new UshahidiIncident[endIndex-startIndex + 1];
		//Inserts incidents into UshahidiIncendetn array
		return (UshahidiIncident[]) sorted.subList(startIndex, endIndex + 1).toArray(returnArray);
	}

	/**
	 * identify prints the incidents that are between startDate
	 * and endDate.
	 * @param pen, a PrintWriter
	 * @param list, an UshahidiClient
	 * @param startDate, a Calendar date
	 * @param endDate, a Calendar date
	 * @throws Exception
	 */
	public static void identify(PrintWriter pen, UshahidiClient list, Calendar startDate, Calendar endDate) throws Exception{
		UshahidiIncident[] relevant = identifyArray(list, startDate, endDate);
		for (int i = 0; i < relevant.length; i++){
			printIncident(pen, relevant[i]);
		}
	}
}

