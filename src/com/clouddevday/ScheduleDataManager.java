package com.clouddevday;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

/**
 * The ScheduleDataManager class is used to read in the Schedule Data at application start.
 * It has member variables and methods to read the data from either a remote URL, a 
 * local data file, or a resource string.
 * 
 * @author Don Ward
 */
public class ScheduleDataManager {
	
	private String[] timeRoomsPresenters;
	private int remoteVersionNumber;
	private int localVersionNumber;
	private static String remoteURL = "http://clouddevday-schedule.s3-website-us-east-1.amazonaws.com/";
	private static String localDataFileName = "CloudDevDaySchedule.xml";
	private static String rawRemoteData = "";
	private ParsedScheduleDataSet remoteParsedDataSet;
	private ParsedScheduleDataSet localParsedDataSet;
	
	/**
	 * getTimeRoomPresenters - used to return the current Schedule Data
	 * 
	 *@return String[] timeRoomPresenters
	 */
	public String[] getTimeRoomsPresenters()
	{
		return timeRoomsPresenters;
	}
	
	/**
	 * checkForAndParseRemoteData - opens a connection to the remote URL and 
	 * checks for remote data. If it exists, then it stores the raw data in the rawRemoteData 
	 * member variable and also parses the data with an XML parser
	 * 
	 * boolean dataExists 
	 */
	
	public boolean checkForAndParseRemoteData()
	{
		try {
			/* Create a URL we want to load some xml-data from. */
			URL url = new URL(remoteURL);

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			ScheduleDataHandler myHandler = new ScheduleDataHandler();
			xr.setContentHandler(myHandler);

			/*Read in raw data to rawRemoteData*/
			BufferedInputStream bfr =  new BufferedInputStream(url.openStream());
			 
			//create a byte array
			 byte[] contents = new byte[1024];
			 int bytesRead=0;
			 
			 while((bytesRead = bfr.read(contents)) != -1)
			 {
			 rawRemoteData = rawRemoteData + new String(contents, 0, bytesRead);
			 }
			 
			 /* Parse the xml-data from our URL. */
			xr.parse(new InputSource(url.openStream()));
			/* Parsing has finished. */

			/* Our ParseScheduleHandler now provides the parsed data to us. */
			remoteParsedDataSet = myHandler.getParsedData();
			
			/*Success - return true*/
			return true;
			
		} catch (Exception e) {
			return false;
		}
		
	}
	
	/**
	 * getRemoteVersionNumber - read the RemoteVersionNumber from the remote URL
	 * 
	 * @return int remoteVersionNumber
	 */
	public int getRemoteVersionNumber(){
		/*Get the remote version number from the parsed data and return it.*/
			remoteVersionNumber = remoteParsedDataSet.getVersion();
			return remoteVersionNumber;			
	}
	
	/**
	 * getLocalVersionNumber - read the LocalVersionNumber from the Local File
	 * 
	 * @return int localVersionNumber
	 */
	public int getLocalVersionNumber(){
		try{
			FileInputStream fis = MyApp.getContext().openFileInput(localDataFileName);

			/* Get a SAXParser from the SAXPArserFactory. */
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			/* Get the XMLReader of the SAXParser we created. */
			XMLReader xr = sp.getXMLReader();
			/* Create a new ContentHandler and apply it to the XML-Reader */
			ScheduleDataHandler myHandler = new ScheduleDataHandler();
			xr.setContentHandler(myHandler);
			 
			 /* Parse the xml-data from our URL. */
			InputStream in = new BufferedInputStream(fis);
			xr.parse(new InputSource(in));
			/* Parsing has finished. */

			/* Our ParseScheduleHandler now provides the parsed data to us. */
			localParsedDataSet = myHandler.getParsedData();
	
			/*Return the version number*/
			localVersionNumber = localParsedDataSet.getVersion();
			return localVersionNumber;
			
		} catch (Exception e) {
			return 0;
		}
	}
	
	/**
	 * localDataFileExists - return a boolean indicating whether the Local File for data
	 * storage exists.
	 * 
	 * @return boolean
	 * 
	 */
	public boolean localDataFileExists(){
		String[] filesInContext = MyApp.getContext().fileList();
		boolean fileExists = false;
		if(filesInContext.length > 0)
		{
		for(int i=0; i < filesInContext.length; i++)
		{
			if (filesInContext[i].contains(localDataFileName))
			fileExists = true;
		}
		}
		//MyApp.getContext().deleteFile(localDataFileName);
		return fileExists;
	}
	
	/**
	 * readRemoteData - reads from the remote URL the schedule data
	 * 
	 * @return boolean success
	 */
	public boolean readRemoteData(){
			/* Assign the results */
			timeRoomsPresenters = remoteParsedDataSet.getTimeRoomPresentersArray();
			return true;
	}

	/**
	 * readLocalDataFileData - reads from the local data file the schedule data
	 * 
	 * @return boolean success
	 */
public boolean readLocalDataFile(){
	timeRoomsPresenters = localParsedDataSet.getTimeRoomPresentersArray();
	return true;
	
}

/**
 * readLocalResourceStringData - reads the schedule data from the local resource
 * string
 * 
 * @return boolean success
 */
public void readLocalResourceStringData(){
	timeRoomsPresenters = MyApp.getContext().getResources().getStringArray(R.array.time_room_presenter_array);
}

/**
 * storeRemoteDatatoLocalDataFile - stores remote data to the Local Data file which is an
 * Internal File private to this application
*
*@return boolean success
*/
public boolean storeRemoteDatatoDataFile(){
	try
	{
		FileOutputStream fos  = MyApp.getContext().openFileOutput(localDataFileName,MyApp.getContext().MODE_PRIVATE);
		fos.write(rawRemoteData.getBytes());
		fos.close();
		return true;
	}
	catch (Exception e){
		return false;
	}
}




}