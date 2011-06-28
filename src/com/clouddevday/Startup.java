package com.clouddevday;

import java.util.HashMap;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

/**
 * The Startup Class is used to initialize the Presentations class with data. It
 * also initializes the main Tab Host for the applications and displays the
 * Schedule Activity as the first Tab in the Tab Host.
 * 
 * NOTE - It is the second Activity called in the start of the CloudDevDay
 * Schedule application.
 * 
 * @author Don Ward
 */
public class Startup extends TabActivity {

	/**
	 * currentTab is an integer representing the current tab displayed. It is
	 * used by the TimeSlotActivity class to display the proper timeslot tab.
	 */
	public static int currentTab;
	/**
	 * scheduleItems is a array of the items to add to the ListView for the
	 * ScheduleActivity.
	 */
	public static String[] scheduleItems;
	/**
	 * timeSlotMap has key = String timeSlot, value = HashMap HashMap has key =
	 * room, value = presenter
	 */
	public static HashMap<String, HashMap<String, String>> timeSlotMap = new HashMap<String, HashMap<String, String>>();
	/**
	 * presentation_titles is a HashMap where the key=presenter,
	 * value=presentation title
	 */
	public static HashMap<String, String> presentation_titles = new HashMap<String, String>();
	/**
	 * The currentTimeSlot member holds the value of the Current Displayed Time
	 * Slot Data for the TimeSlot Tab.
	 */
	public static String currentTimeSlot;
	/**
	 * The timeSlots member holds the Time Slot Names for the Schedule.
	 */
	public static String[] timeSlots;
	/**
	 * presentations - represents all of the Presentations in the schedule
	 */
	public static Presentations presentations;
	/**
	 * timeSlotTimes is an array of the timeSlotTimes used for the individual
	 * tabs in the Tabhost.
	 */
	private String[] timeSlotTimes;
	private String[] presenters;
	private String[] presentationTitles;
	/**
	 * timeRoomPresenters is an array of the time/room/presenters combinations
	 */
	public static String[] timeRoomsPresenters;

	/**
	 * onCreate() - Called when the activity is first created. It will create
	 * the tabs in the main Tab Host and create the activities displayed when
	 * they are selected.
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*
		 * Initialize Time Slot Names, Time Slot Times, and other data from
		 * local String Resources NOTE: This is data that is not dynamic to the
		 * application. The schedule data containing time/room/presenter data is
		 * dynamically read the either a local file or a remote URL.
		 */
		readLocalData();

		// Resource object to get Drawables
		Resources res = getResources();
		// The activity TabHost
		TabHost tabHost = getTabHost();
		// Reusable TabSpec for each tab
		TabHost.TabSpec spec;
		// Reusable Intent for each tab
		Intent intent;
		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, ScheduleActivity.class);
		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost
				.newTabSpec("schedule")
				.setIndicator(" Schedule ",
						res.getDrawable(R.drawable.ic_tab_schedule))
				.setContent(intent);
		tabHost.addTab(spec);

		// Loop through the TimeSlots and add TimeSlot tabs for each
		for (int i = 0; i < timeSlots.length; i++) {
			Intent myintent = new Intent().setClass(this,
					TimeSlotActivity.class);
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

	/**
	 * onNewIntent is used when a used selects a tab in the TabHost.
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		getTabHost().setCurrentTab(currentTab);
	}

	/*
	 * Initialize Time Slot Names, Time Slot Times, and other data from local
	 * String Resources NOTE: This is data that is not dynamic to the
	 * application. The schedule data containing time/room/presenter data is
	 * dynamically read the either a local file or a remote URL.
	 * 
	 * @returns void
	 */
	public void readLocalData() {
		timeSlots = getResources().getStringArray(R.array.timeslots_array);
		currentTimeSlot = timeSlots[0];
		scheduleItems = getResources().getStringArray(R.array.schedule_array);
		timeSlotTimes = getResources().getStringArray(R.array.timeslots_times);
		presenters = getResources().getStringArray(R.array.presenters_array);
		presentationTitles = getResources().getStringArray(
				R.array.presenters_titles);
		presentations = new Presentations();
		for (int i = 0; i < presenters.length; i++) {
			presentations.addPresentation(presenters[i], presentationTitles[i],
					"");
		}
		for (int i = 0; i < timeRoomsPresenters.length; i++) {
			String[] entry = timeRoomsPresenters[i].split(",");

			// if the key already exists for this TimeSlot
			HashMap<String, String> newVal = new HashMap<String, String>();
			if (timeSlotMap.containsKey(entry[0])) {
				newVal = timeSlotMap.get(entry[0]);
				newVal.put(entry[1], entry[2]);
			}
			// otherwise this is a new key
			else {
				HashMap<String, String> t = new HashMap<String, String>();
				t.put(entry[1], entry[2]);
				timeSlotMap.put(entry[0], t);
			}
		}
	}
}
