package com.clouddevday;

import java.util.ArrayList;

import android.util.Log;

/**
 * The ParseScheduleDataSet class is used to hold all of the parsed XML data about the schedule * 
 * @author Don Ward
 *
 */
public class ParsedScheduleDataSet {
	
	/**
	 * Log TAG
	 */
	private static final String TAG = "ParsedScheduleDataSet";
	
	private String extractedString = null;
	private ArrayList<String> roomsArray = new ArrayList<String>();
	private ArrayList<String> timesArray = new ArrayList<String>();
	private ArrayList<String> presentersArray = new ArrayList<String>();
	private ArrayList<String> presenterDescriptionsArray = new ArrayList<String>();
	private ArrayList<String> timeRoomPresentersArray = new ArrayList<String>();
	private ArrayList<String> presentationTitlesArray = new ArrayList<String>();
	private int version;	
	private int extractedInt = 0;

	public String getExtractedString() {
		return extractedString;
	}
	public void setExtractedString(String extractedString) {
		this.extractedString = extractedString;
	}

	public int getExtractedInt() {
		return extractedInt;
	}
	public void setExtractedInt(int extractedInt) {
		this.extractedInt = extractedInt;
	}
	
	public String toString(){
		return "ExtractedString = " + this.extractedString
				+ "nExtractedInt = " + this.extractedInt;
	}
	
	public void setVersion(String readVersion)
	{
		this.version = Integer.parseInt(readVersion);
	}
	
	public int getVersion()
	{
		return version;
	}
	public void addRoom(String room)
	{
		roomsArray.add(room);
		Log.i(TAG,"Adding to rooms: "+ room);
	}

	public String[] getRoomsArray()
	{
		String [] rooms = new String[roomsArray.size()];
		int i=0;
		for (String s:  roomsArray)
		{
			rooms[i] = s;
			i++;
		}
		return rooms;
	}

	public void addTime(String time)
	{
		timesArray.add(time);
		Log.i(TAG,"Adding to timeslottimes: "+ time );
	}

	public String[] gettimeSlotTimesArray()
	{
		String [] times = new String[timesArray.size()];
		int i=0;
		for (String s:  timesArray)
		{
			times[i] = s;
			i++;
		}
		return times;
	}

	public void addPresenter(String item)
	{
		presentersArray.add(item);
		Log.i(TAG,"Adding to presenters: "+ item );
	}

	public String[] getPresentersArray()
	{
		String [] items = new String[presentersArray.size()];
		int i=0;
		for (String s:  presentersArray)
		{
			items[i] = s;
			i++;
		}
		return items;
	}
	
	public void addDesc(String item)
	{
		presenterDescriptionsArray.add(item);
		Log.i(TAG,"Adding to descriptions: "+ item );
	}

	public String[] getDescArray()
	{
		String [] items = new String[presenterDescriptionsArray.size()];
		int i=0;
		for (String s:  presenterDescriptionsArray)
		{
			items[i] = s;
			i++;
		}
		return items;
	}
	
	public void addTitle(String item)
	{
		presentationTitlesArray.add(item);
		Log.i(TAG,"Adding to descriptions: "+ item );
	}

	public String[] getTitleArray()
	{
		String [] items = new String[presentationTitlesArray.size()];
		int i=0;
		for (String s:  presentationTitlesArray)
		{
			items[i] = s;
			i++;
		}
		return items;
	}
	public void addTimeRoomPresenter(String item)
	{
		timeRoomPresentersArray.add(item);
		Log.i(TAG,"Adding to timeRoomPresenters: "+ item );
	}

	public String[] getTimeRoomPresentersArray()
	{
		String [] items = new String[timeRoomPresentersArray.size()];
		int i=0;
		for (String s:  timeRoomPresentersArray)
		{
			items[i] = s;
			i++;
		}
		return items;
	}
	
}