package com.clouddevday;

import java.util.ArrayList;
import android.util.Log;

/**
 * The ParseScheduleDataSet class is used to hold all of the parsed XML data
 * about the schedule.
 * 
 * @author Don Ward
 */
public class ParsedScheduleDataSet {

	/**
	 * Log TAG for Android log messages
	 */
	private static final String TAG = "ParsedScheduleDataSet";

	/**
	 * roomsArray is an ArrayList of the Rooms used in the conference
	 */
	private ArrayList<String> roomsArray = new ArrayList<String>();
	/**
	 * timesArray is an ArrayList of the Times for the conference
	 */
	private ArrayList<String> timesArray = new ArrayList<String>();
	/**
	 * presentersArray is an ArrayList of the Presenters in the conference
	 */
	private ArrayList<String> presentersArray = new ArrayList<String>();
	/**
	 * presenterDescriptionsArray is an ArrayList of the Descriptions for the
	 * Presenters in the conference
	 */
	private ArrayList<String> presenterDescriptionsArray = new ArrayList<String>();
	/**
	 * timeRoomPresentersArray is an ArrayList of the time/room/presenter
	 * combinations used in the conference
	 */
	private ArrayList<String> timeRoomPresentersArray = new ArrayList<String>();
	/**
	 * presentationTitlesArray is an ArrayList of the presentration titles in
	 * the conference
	 */
	private ArrayList<String> presentationTitlesArray = new ArrayList<String>();
	/**
	 * version is the Version Number of the Schedule Data. The local version
	 * number is compared to the remote URL version number to determine whether
	 * to read the remote URL's data.
	 */
	private int version;

	/**
	 * Setter for the version number.
	 * 
	 * @param readVersion
	 */
	public void setVersion(String readVersion) {
		this.version = Integer.parseInt(readVersion);
	}

	/**
	 * Getter for the version number.
	 * 
	 * @return int version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * addRoom adds a room to the roomsArray.
	 * 
	 * @param room
	 */
	public void addRoom(String room) {
		roomsArray.add(room);
		Log.i(TAG, "Adding to rooms: " + room);
	}

	/**
	 * getRoomsArray returns the current rooms.
	 * 
	 * @return String[] roomsArray
	 */
	public String[] getRoomsArray() {
		String[] rooms = new String[roomsArray.size()];
		int i = 0;
		for (String s : roomsArray) {
			rooms[i] = s;
			i++;
		}
		return rooms;
	}

	/**
	 * addTime adds a time to the time slot array
	 * @param time
	 */
	public void addTime(String time) {
		timesArray.add(time);
		Log.i(TAG, "Adding to timeslottimes: " + time);
	}

	/**
	 * gettimeSlotTimesArray returns an array of the time slot times.
	 * @return String[] timeSlotTimes
	 */
	public String[] gettimeSlotTimesArray() {
		String[] times = new String[timesArray.size()];
		int i = 0;
		for (String s : timesArray) {
			times[i] = s;
			i++;
		}
		return times;
	}

	/**
	 * addPresenter adds a presenter to the presenter array.
	 * @param item
	 */
	public void addPresenter(String item) {
		presentersArray.add(item);
		Log.i(TAG, "Adding to presenters: " + item);
	}

	/**
	 * getPresenterArray returns an array of the presenters.
	 * @return String[] presentersArray
	 */
	public String[] getPresentersArray() {
		String[] items = new String[presentersArray.size()];
		int i = 0;
		for (String s : presentersArray) {
			items[i] = s;
			i++;
		}
		return items;
	}

	/**
	 * addDesc adds a Description to the presentation description array.
	 * @param item
	 */
	public void addDesc(String item) {
		presenterDescriptionsArray.add(item);
		Log.i(TAG, "Adding to descriptions: " + item);
	}

	/**
	 * getDescArray returns an array of the presenter descriptions
	 * @return String[] descriptions
	 */
	public String[] getDescArray() {
		String[] items = new String[presenterDescriptionsArray.size()];
		int i = 0;
		for (String s : presenterDescriptionsArray) {
			items[i] = s;
			i++;
		}
		return items;
	}

	/**
	 * addTitle adds a title to the Titles array.
	 * @param item
	 */
	public void addTitle(String item) {
		presentationTitlesArray.add(item);
		Log.i(TAG, "Adding to descriptions: " + item);
	}

	/**
	 * getTitleArray returns an array of the Titles
	 * @return String[] titles
	 */
	public String[] getTitleArray() {
		String[] items = new String[presentationTitlesArray.size()];
		int i = 0;
		for (String s : presentationTitlesArray) {
			items[i] = s;
			i++;
		}
		return items;
	}

	/**
	 * addTimeRoomPresenter adds a time/room/presenter combination to the array
	 * @param item
	 */
	public void addTimeRoomPresenter(String item) {
		timeRoomPresentersArray.add(item);
		Log.i(TAG, "Adding to timeRoomPresenters: " + item);
	}
	
	/**
	 * getTimeRoomPresenterArray returns the combinations of the time/room/presenters in an array
	 * @return String[] timeRoomPresenters
	 */

	public String[] getTimeRoomPresentersArray() {
		String[] items = new String[timeRoomPresentersArray.size()];
		int i = 0;
		for (String s : timeRoomPresentersArray) {
			items[i] = s;
			i++;
		}
		return items;
	}

}