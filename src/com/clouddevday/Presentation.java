package com.clouddevday;
/**
 * The Presentation class represents encapsulates the presenter, presentation title, and description for a 
 * given presentation. The time slot and room of the presentation is not included in this class because these
 * can and will change leading up to the conference. Thus this info is read from an external source. Also 
 * the same presentation may be done in multiple time slots and rooms. This is another reason for the 
 * separation of this information.
 * 
 * @author Don Ward
 *
 */
public class Presentation {
	
/**
 * String with the presenters name.	
 */
private String presenter;
/**
 * String with the presentations title.
 */
private String title;
/**
 * String with the presentations description.
 */
private String description;

/**
 * Getter for the presenter member variable.
 * @return String Presenter
 */
public String getPresenter() {
	return presenter;
}
/**
 * Setter for the presenter member variable.
 * @param presenter
 */
public void setPresenter(String presenter) {
	this.presenter = presenter;
}
/**
 * Getter for the presentation title member variable.
 * @return String Title
 */
public String getTitle() {
	return title;
}
/**
 * Setter for the presentation title member variable.
 * @param title
 */
public void setTitle(String title) {
	this.title = title;
}
/**
 * Getter for the presentation description.
 * @return String Description
 */
public String getDescription() {
	return description;
}
/**
 * Setter for the presentation description
 * @param description
 */
public void setDescription(String description) {
	this.description = description;
}
}
