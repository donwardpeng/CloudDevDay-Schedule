package com.clouddevday;

import java.net.URL;
import java.util.HashMap;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import com.clouddevday.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * The Startup Class is used to initialize the Presentations class with data. It also initializes the Tab Host and displays the Schedule Activity
 * as the first Tab Linear Layout. 
 * 
 * It is the second Activity called in the start of the CloudDevDay Schedule application. 
 * 
 * @author Don Ward
 */
public class Startup extends TabActivity {

	 /*timeSlotMap has key = String timeSlot, value = HashMap HashmMap has key =
	 room, value = presenter*/
	public static int currentTab;
	public static String[] scheduleItems;
	public static HashMap<String, HashMap<String, String>> timeSlotMap = new HashMap<String, HashMap<String, String>>();
	public static HashMap<String, String> presentation_titles = new HashMap<String, String>();
	/**
	*The currentTimeSlot member holds the value of the Current Displayed Time Slot Data for the TimeSlot Tab.
	*/
	public static String currentTimeSlot;
	/**
	*The timeSlots member holds the Time Slot Names for the Schedule.
	*/
	public static String[] timeSlots;
	/**
	*The newPresentations member is a object of type Presentations and  represents all of the 
	*presentation data - presenter name, presentation title, and presentation description.
	*/
	public static Presentations presentations;
	private String[] timeSlotTimes;
	private String[] presenters;
	private String[] presentationTitles;	
	public static String[] timeRoomsPresenters;

	/**Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*Initialize Time Slot name, Time Slot Times, and other data from local String Resources*/
		readLocalData();
		
		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Reusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ScheduleActivity.class);
		
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("schedule").setIndicator(" Schedule ",res.getDrawable(R.drawable.ic_tab_schedule)).setContent(intent);
		tabHost.addTab(spec);

		// Loop through the TimeSlots and add TimeSlot tabs for each
		for (int i = 0; i < timeSlots.length; i++) {
			Intent myintent = new Intent().setClass(this, TimeSlotActivity.class);
			myintent.putExtra("intentTimeSlotName", timeSlots[i]);

			if (i == 0) {
				spec = tabHost
				.newTabSpec(timeSlots[i])
				.setIndicator(" " + timeSlotTimes[i] + " ",
						res.getDrawable(R.drawable.ic_tab_slot1))
						.setContent(myintent);
			} else if (i == 1) {
				spec = tabHost
				.newTabSpec(timeSlots[i])
				.setIndicator("  " + timeSlotTimes[i] + "  ",
						res.getDrawable(R.drawable.ic_tab_slot2))
						.setContent(myintent);
			} else if (i == 2) {
				spec = tabHost
				.newTabSpec(timeSlots[i])
				.setIndicator("  " + timeSlotTimes[i] + "  ",
						res.getDrawable(R.drawable.ic_tab_slot3))
						.setContent(myintent);
			} else if (i == 3) {
				spec = tabHost
				.newTabSpec(timeSlots[i])
				.setIndicator("  " + timeSlotTimes[i] + "  ",
						res.getDrawable(R.drawable.ic_tab_slot4))
						.setContent(myintent);
			} else if (i == 4) {
				spec = tabHost
				.newTabSpec(timeSlots[i])
				.setIndicator("  " + timeSlotTimes[i] + "  ",
						res.getDrawable(R.drawable.ic_tab_slot5))
						.setContent(myintent);
			} else {
				spec = tabHost
				.newTabSpec(timeSlots[i])
				.setIndicator("  " + timeSlotTimes[i] + "  ",
						res.getDrawable(R.drawable.ic_tab_slot1))
						.setContent(myintent);
			}
			tabHost.addTab(spec);
		}
		// Set the default starting tab to 0
		currentTab = 0;
		tabHost.setCurrentTab(currentTab);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		getTabHost().setCurrentTab(currentTab);
	}

	/**
	 * Called to read presentation data from the local String resources. 
	 * 
	 * @returns void
	 */
	public void readLocalData() {
		timeSlots = getResources().getStringArray(R.array.timeslots_array);
		currentTimeSlot = timeSlots[0];
		scheduleItems=getResources().getStringArray(R.array.schedule_array);
		timeSlotTimes = getResources().getStringArray(R.array.timeslots_times);
		presenters = getResources().getStringArray(R.array.presenters_array);
		presentationTitles = getResources().getStringArray(R.array.presenters_titles);

		presentations = new Presentations();
		for (int i = 0; i < presenters.length; i++) {
			presentations.addPresentation(presenters[i],
					presentationTitles[i], "");
		}

		for (int i = 0; i < timeRoomsPresenters.length; i++) {
			String[] entry = timeRoomsPresenters[i].split(",");

			// if the key already exists for this TimeSlot
			HashMap <String,String>newVal = new HashMap<String, String>();
			if (timeSlotMap.containsKey(entry[0])) {
				newVal = timeSlotMap.get(entry[0]);
				newVal.put(entry[1], entry[2]);
			}
			// otherwise this is a new key
			else {
				HashMap <String,String> t = new HashMap<String, String>();
				t.put(entry[1], entry[2]);
				timeSlotMap.put(entry[0], t);
			}
		}
		}

//	/**
//	 * Called to read remote data presentation data from a url.
//	 * 
//	 * @return void
//	 */
//	public static boolean readRemoteData() {
//		try {
//			/* Create a URL we want to load some xml-data from. */
//			URL url = new URL("http://clouddevday-schedule.s3-website-us-east-1.amazonaws.com/");
//
//			/* Get a SAXParser from the SAXPArserFactory. */
//			SAXParserFactory spf = SAXParserFactory.newInstance();
//			SAXParser sp = spf.newSAXParser();
//
//			/* Get the XMLReader of the SAXParser we created. */
//			XMLReader xr = sp.getXMLReader();
//			/* Create a new ContentHandler and apply it to the XML-Reader */
//			ScheduleDataHandler myHandler = new ScheduleDataHandler();
//			xr.setContentHandler(myHandler);
//
//			/* Parse the xml-data from our URL. */
//			xr.parse(new InputSource(url.openStream()));
//			/* Parsing has finished. */
//
//			/* Our ParseScheduleHandler now provides the parsed data to us. */
//			ParsedScheduleDataSet parsedDataSet = myHandler.getParsedData();
//
//			/* Assign the results */
//			timeRoomsPresenters = parsedDataSet.getTimeRoomPresentersArray();
//			return true;
//		} catch (Exception e) {
//			return false;
//		}
//	}
}
