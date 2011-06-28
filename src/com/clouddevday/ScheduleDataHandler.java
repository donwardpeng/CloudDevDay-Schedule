package com.clouddevday;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * The ScheduleDataHandler class extends the SAX Parser DefaultHandler class for
 * use in reading in Schedule Data from either a remote URL or the local data
 * file.
 * 
 * @author DonWard
 * 
 */
public class ScheduleDataHandler extends DefaultHandler {

	/**
	 * used to indicate when in a Room Tag
	 */
	private boolean in_roomtag = false;
	/**
	 * used to indicate when in a Time Tag
	 */
	private boolean in_timetag = false;
	/**
	 * used to indicate when in a Presenter Tag
	 */
	private boolean in_presentertag = false;
	/**
	 * used to indicate when in a Description Tag
	 */
	private boolean in_desctag = false;
	/**
	 * used to indicate when in a Title Tag
	 */
	private boolean in_titletag = false;
	/**
	 * used to indicate when in a time room presenter Tag (trp)
	 */
	private boolean in_trptag = false;
	/**
	 * used to indicate when in a Version Tag
	 */
	private boolean in_versiontag = false;

	/**
	 * myParseScheduleDataSet is a parsed representation of all of the XML data read
	 */
	private ParsedScheduleDataSet myParsedScheduleDataSet = new ParsedScheduleDataSet();

	/**
	 * Getter for the parsedScheduleDataSet
	 * 
	 * @return ParsedScheduleDataSet
	 */
	public ParsedScheduleDataSet getParsedData() {
		return this.myParsedScheduleDataSet;
	}

	/**
	 * startDocument called at the beginning of the XML document.
	 */
	@Override
	public void startDocument() throws SAXException {
		this.myParsedScheduleDataSet = new ParsedScheduleDataSet();
	}

	/**
	 * endDocument called at the end of the XML document.
	 */
	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	/** 
	 * startElement called at the start of an XML element.
	 * The proper 'flag' is set depending on the start tags name.
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equals("version")) {
			this.in_versiontag = true;
		} else if (localName.equals("room")) {
			this.in_roomtag = true;
		} else if (localName.equals("time")) {
			this.in_timetag = true;
		} else if (localName.equals("presenter")) {
			this.in_presentertag = true;
		} else if (localName.equals("desc")) {
			this.in_desctag = true;
		} else if (localName.equals("presentation")) {
			this.in_titletag = true;
		} else if (localName.equals("trp")) {
			this.in_trptag = true;
		} else {
			// do nothing
		}
	}

	/** 
	 * endElement called at the end of an XML element.
	 * The proper 'flag' is reset depending on the end tags name.
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("version")) {
			this.in_versiontag = false;
		} else if (localName.equals("room")) {
			this.in_roomtag = false;
		} else if (localName.equals("time")) {
			this.in_timetag = false;
		} else if (localName.equals("presenter")) {
			this.in_presentertag = false;
		} else if (localName.equals("desc")) {
			this.in_desctag = false;
		} else if (localName.equals("presentation")) {
			this.in_titletag = false;
		} else if (localName.equals("trp")) {
			this.in_trptag = false;
		} else {
			// do nothing
		}
	}

	/** 
	 * characters handles the data between XML start and end tags.
	 * It adds the data to the myParsedScheduleDataSet member variable.
	 */
	@Override
	public void characters(char ch[], int start, int length) {
		if (this.in_versiontag) {
			myParsedScheduleDataSet.setVersion(new String(ch, start, length));
		}
		if (this.in_roomtag) {
			myParsedScheduleDataSet.addRoom(new String(ch, start, length));
		} else if (this.in_timetag) {
			myParsedScheduleDataSet.addTime(new String(ch, start, length));
		} else if (this.in_presentertag) {
			myParsedScheduleDataSet.addPresenter(new String(ch, start, length));
		} else if (this.in_desctag) {
			myParsedScheduleDataSet.addDesc(new String(ch, start, length));
		} else if (this.in_titletag) {
			myParsedScheduleDataSet.addTitle(new String(ch, start, length));
		} else if (this.in_trptag) {
			myParsedScheduleDataSet.addTimeRoomPresenter(new String(ch, start,
					length));
		}
	}
}