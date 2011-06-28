package com.clouddevday;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Presentation Iterator is used to implement the Iterator Design Pattern to allow 
 * iteration over the Presentation Objects in the application. It is used in the Presentations class
 * to provide an Iterator.
 * 
 * @author DonWard
 *
 */
public class PresentationIterator implements Iterator<Presentation> {
	
	/**
	 * Arraylist of Presentation Objects.
	 */
	private ArrayList<Presentation> p;

	/**
	 * Iterator of over the Presentation Objects.
	 */
	private Iterator<Presentation> i;
	
	/**
	 * Constructor to setup the Iterator.
	 * @param presentations
	 */
	public PresentationIterator(ArrayList<Presentation> presentations){
		p = presentations;
		i = p.iterator();
	}
	/**
	 * hasNext returns the Presentation object.
	 */
	@Override
	public boolean hasNext() {
		return i.hasNext();
	}

	/**
	 * next return the next Presentation object.
	 */
	@Override
	public Presentation next() {
		return (Presentation)i.next();
	}

	@Override
	public void remove() {
	}	
}
