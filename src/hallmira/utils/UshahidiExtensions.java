package hallmira.utils;

import java.io.PrintWriter;
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
	pen.println("DESCRIPTION");
	pen.println("Location: "+ inc.getLocation());
	pen.println("Status: (" + inc.getMode()+ ", "+ inc.getActive() + 
			", " + inc.getVerified() + ")");
	
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
}
