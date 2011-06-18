package com.clouddevday;
/**
 * The Presentation class represents encapsulates the presenter, presentation title, and description for a 
 * given presentation. The time slot and room of the presentation is not included in this class because these
 * can and will change leading up to the conference. Thus this info is read from an external source. Also 
 * the same presentation may be done in multiple timeslots and rooms. This is another reason for the 
 * separation of this information.
 * 
 * @author Don Ward
 *
 */
public class Presentation {
private String presenter;
private String title;
private String description;

//Getter and Setters
public String getPresenter() {
	return presenter;
}
public void setPresenter(String presenter) {
	this.presenter = presenter;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
}
