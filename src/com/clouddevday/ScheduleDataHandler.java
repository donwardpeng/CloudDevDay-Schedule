package com.clouddevday;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ScheduleDataHandler extends DefaultHandler {

	// ===========================================================
	// Fields
	// ===========================================================

	private boolean in_datatag = false;
	private boolean in_roomstag = false;
	private boolean in_roomtag = false;
	private boolean in_timeslottimestag = false;
	private boolean in_timetag = false;
	private boolean in_presenterstag = false;
	private boolean in_presentertag = false;
	private boolean in_presenterdesctag = false;
	private boolean in_desctag = false;
	private boolean in_presentationtitletag = false;
	private boolean in_titletag = false;
	private boolean in_timeroompresenterstag = false;
	private boolean in_trptag = false;
	private boolean in_scheduletag = false;
	private boolean in_slottag = false;

	private ParsedScheduleDataSet myParsedScheduleDataSet = new ParsedScheduleDataSet();

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	public ParsedScheduleDataSet getParsedData() {
		return this.myParsedScheduleDataSet;
	}

	// ===========================================================
	// Methods
	// ===========================================================
	@Override
	public void startDocument() throws SAXException {
		this.myParsedScheduleDataSet = new ParsedScheduleDataSet();
	}

	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	/**
	 * Gets be called on opening tags like: <tag> Can provide attribute(s), when
	 * xml was like: <tag attribute="attributeValue">
	 */
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes atts) throws SAXException {
		if (localName.equals("data")) {
			this.in_datatag = true;
		} else if (localName.equals("rooms")) {
			this.in_roomstag = true;
		} else if (localName.equals("room")) {
			this.in_roomtag = true;
		} else if (localName.equals("timeslottimes")) {
			this.in_timeslottimestag = true;
		} else if (localName.equals("time")) {
			this.in_timetag = true;
		} else if (localName.equals("presenters")) {
			this.in_presenterstag = true;
		} else if (localName.equals("presenter")) {
			this.in_presentertag = true;
		} else if (localName.equals("presenterdesc")) {
			this.in_presenterdesctag = true;
		} else if (localName.equals("desc")) {
			this.in_desctag = true;
		} else if (localName.equals("presentations")) {
			this.in_presentationtitletag = true;
		} else if (localName.equals("presentation")) {
			this.in_titletag = true;
		} else if (localName.equals("timeroompresenters")) {
			this.in_timeroompresenterstag = true;
		} else if (localName.equals("trp")) {
			this.in_trptag = true;
		} else if (localName.equals("schedule")) {
			this.in_scheduletag = true;
		} else if (localName.equals("slot")) {
			this.in_slottag = true;
		} else {
		}
	}

	/**
	 * Gets be called on closing tags like: </tag>
	 */
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("data")) {
			this.in_datatag = false;
		} else if (localName.equals("rooms")) {
			this.in_roomstag = false;
		} else if (localName.equals("room")) {
			this.in_roomtag = false;
		} else if (localName.equals("timeslottimes")) {
			this.in_timeslottimestag = false;
		} else if (localName.equals("time")) {
			this.in_timetag = false;
		} else if (localName.equals("presenters")) {
			this.in_presenterstag = false;
		} else if (localName.equals("presenter")) {
			this.in_presentertag = false;
		} else if (localName.equals("presenterdesc")) {
			this.in_presenterdesctag = false;
		} else if (localName.equals("desc")) {
			this.in_desctag = false;
		} else if (localName.equals("presentations")) {
			this.in_presentationtitletag = false;
		} else if (localName.equals("presentation")) {
			this.in_titletag = false;
		} else if (localName.equals("timeroompresenters")) {
			this.in_timeroompresenterstag = false;
		} else if (localName.equals("trp")) {
			this.in_trptag = false;
		} else if (localName.equals("schedule")) {
			this.in_scheduletag = false;
		} else if (localName.equals("slot")) {
			this.in_slottag = false;
		} else {
		}
	}

	/**
	 * Gets be called on the following structure: <tag>characters</tag>
	 */
	@Override
	public void characters(char ch[], int start, int length) {
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
		}else if (this.in_trptag) {
		myParsedScheduleDataSet.addTimeRoomPresenter(new String(ch, start, length));
	}

	}
}