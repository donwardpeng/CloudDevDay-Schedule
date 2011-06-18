package com.clouddevday;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.AsyncTask;

public class LoadScheduleDataTask extends AsyncTask {

	@Override
	protected Object doInBackground(Object... params) {
			try {
				/* Create a URL we want to load some xml-data from. */
				URL url = new URL("http://www.greymatterworx.com/MobiDevDay/data.xml");

				/* Get a SAXParser from the SAXPArserFactory. */
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();

				/* Get the XMLReader of the SAXParser we created. */
				XMLReader xr = sp.getXMLReader();
				/* Create a new ContentHandler and apply it to the XML-Reader */
				ScheduleDataHandler myHandler = new ScheduleDataHandler();
				xr.setContentHandler(myHandler);

				/* Parse the xml-data from our URL. */
				xr.parse(new InputSource(url.openStream()));
				/* Parsing has finished. */

				/* Our ParseScheduleHandler now provides the parsed data to us. */
				ParsedScheduleDataSet parsedDataSet = myHandler.getParsedData();

				/* Assign the results */
				Startup.timeRoomsPresenters = parsedDataSet.getTimeRoomPresentersArray();
			} catch (Exception e) {
			}
		return null;
	}

	
	
	

}
