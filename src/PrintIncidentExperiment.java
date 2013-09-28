import java.io.PrintWriter;

import hallmira.utils.UshahidiExtensions;
import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncidentList;

public class PrintIncidentExperiment {
	
	//from Assignment 5 instructions
    public static void main(String[] args) throws Exception{
    	//create printwriter
    	PrintWriter pen = new PrintWriter(System.out, true);
        // A newly created incident
        UshahidiIncident muffin = new UshahidiIncident(1, "Muffin");
        
        UshahidiExtensions.printIncident(pen, muffin);
        UshahidiIncidentList list = UshahidiExtensions.incidentList();
        // One from a list
        while (list.hasMoreIncidents()){
        	UshahidiExtensions.printIncident(pen, list.nextIncident());
        }

        // One that requires connecting to the server
        //UshahidiClient webclient = new UshahidiWebClient("https://farmersmarket.crowdmap.com");
        //UshahidiExtensions.printIncident(pen, webclient.nextIncident());
    } // main(String[])
    

}
