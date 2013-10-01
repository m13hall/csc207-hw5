package hallmira.utils;
import java.io.PrintWriter;
import java.util.Calendar;
import hallmira.utils.UshahidiExtensions;

import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiLocation;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;

public class PrintIncidentExperiment {
	
	//from Assignment 5 instructions
    public static void main(String[] args) throws Exception{
    	//create printwriter
    	PrintWriter pen = new PrintWriter(System.out, true);
        // A newly created incident
    	
        
        UshahidiIncidentList list = UshahidiExtensions.incidentList();
        // One from a list
       /* while (list.hasMoreIncidents()){
        	UshahidiExtensions.printIncident(pen, list.nextIncident());
        }*/
        UshahidiExtensions.printList(pen, UshahidiExtensions.orderById(TestUshahidis.createIncidents()));
        
        UshahidiExtensions.getExtremes(pen, TestUshahidis.createIncidents());
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(1994, 6, 13);
        end.set(2013, 9, 30);
        UshahidiExtensions.identify(pen, TestUshahidis.createIncidents(),start, end);
        // One that requires connecting to the server
        //UshahidiClient webclient = new UshahidiWebClient("https://farmersmarket.crowdmap.com");
        //UshahidiExtensions.printIncident(pen, webclient.nextIncident());
    } // main(String[])
    

}
