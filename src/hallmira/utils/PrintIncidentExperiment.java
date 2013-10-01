package hallmira.utils;
import java.io.PrintWriter;
import java.util.Calendar;
import hallmira.utils.UshahidiExtensions;
import edu.grinnell.glimmer.ushahidi.UshahidiClient;
import edu.grinnell.glimmer.ushahidi.UshahidiIncident;
import edu.grinnell.glimmer.ushahidi.UshahidiLocation;
import edu.grinnell.glimmer.ushahidi.UshahidiUtils;
import edu.grinnell.glimmer.ushahidi.UshahidiWebClient;


/**
 * 
 * @author southpaw14 -- Andrew Kelley
 * @author m13hall -- Mira Hall
 * 
 * This class is for running experiments that use 
 * UshahidiExtensions and the UshahidiIncident list from
 * TestUshahidis.
 *
 */
public class PrintIncidentExperiment {
	
	//from Assignment 5 instructions
    public static void main(String[] args) throws Exception{
    	//create printwriter
    	PrintWriter pen = new PrintWriter(System.out, true);
        // A newly created incident
    	
    	// A few basic incidents
    	pen.println("A few basics incidents:");
        UshahidiExtensions.printIncident(pen, UshahidiUtils.SAMPLE_INCIDENT);
        UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());
        UshahidiExtensions.printIncident(pen, UshahidiUtils.randomIncident());

        // A newly created incident
        Calendar dateA = Calendar.getInstance();
		dateA.set(2013, 10, 19);
		UshahidiLocation locationA = new UshahidiLocation(1, "St. Louis");
        UshahidiIncident myIncident = new UshahidiIncident(1, "St. Louis Cardinals Baseball Game",
        		dateA, locationA, "Cards win!");
        pen.println("A sample of a newly created incident:");
        UshahidiExtensions.printIncident(pen, myIncident);

       
        // One from a list
        pen.println("An incident from a list:");
        UshahidiClient client = UshahidiUtils.SAMPLE_CLIENT;
        UshahidiExtensions.printIncident(pen, client.nextIncident());

        
        // One that requires connecting to the server
        pen.println("An incident from the internet");
        UshahidiClient webclient = new UshahidiWebClient("https://farmersmarket.crowdmap.com");
        UshahidiExtensions.printIncident(pen, webclient.nextIncident());
        
    	pen.println("");
        //end Part A tests
        
        /**
         * Part B can be found implemented in TestUshahidis.java as createIncidents().
         */

    	
    	//example of part B implemented
    	
        pen.println("Our list of incidents printed:");
        UshahidiClient list = TestUshahidis.createIncidents();
        UshahidiExtensions.printList(pen, list);
        pen.println("");
        
        //end part B
        
        /**
         * This section is added and shows that our sorting algorithms work.
         */
        //sorted by date, should print A-L in order
        pen.println("Printing list in order by date");
        UshahidiExtensions.printList(pen, UshahidiExtensions.orderByDate(TestUshahidis.createIncidents()));
        pen.println("");
        
        //sorted by Id, should print A-L in order
        pen.println("Printing list in order by Id");
        UshahidiExtensions.printList(pen, UshahidiExtensions.orderById(TestUshahidis.createIncidents()));
        pen.println("");

        //end added section
        
        
        //Part C: Extreme Incidents
        pen.println("Printing of extremes of our own list"); //should be A, and L.
        UshahidiExtensions.getExtremes(pen, TestUshahidis.createIncidents());
        pen.println("");
        //end Part C.
        
        
        /**
         * This next test is the answer to parts D and E. This test takes a starting 
         * and an end date and prints those incidents which fall between the two dates
         * (inclusive).
         */
        pen.println("List of incidents with dates between 6/13/1994 and 9/30/2013");
        Calendar start = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        start.set(1994, 6, 13);
        end.set(2013, 9, 30);
        UshahidiExtensions.identify(pen, TestUshahidis.createIncidents(),start, end);
        pen.println("");
        //end of parts D and E.
        
        
        //One that requires connecting to the server
        pen.println("Prints, by order of date, the incidents from farmersmarket.crowdmap.com");
        UshahidiClient webclient2 = new UshahidiWebClient("https://farmersmarket.crowdmap.com");
        UshahidiExtensions.printList(pen, UshahidiExtensions.orderByDate(webclient2));
        pen.println("\nPrints, by order of Id, the incidents from farmersmarket.crowdmap.com");
        UshahidiClient webclient3 = new UshahidiWebClient("https://farmersmarket.crowdmap.com");
        UshahidiExtensions.printList(pen, UshahidiExtensions.orderById(webclient3));
        pen.println("\nEnd of Testing!");
        //end of testing
        
    } // main(String[])
    

}
