package com.clouddevday;

import java.util.ArrayList;
import java.util.Iterator;

public class PresentationIterator implements Iterator<Presentation> {
	private ArrayList<Presentation> p;

	private Iterator<Presentation> i;
	public PresentationIterator(ArrayList<Presentation> presentations){
		p = presentations;
		i = p.iterator();
	}
	@Override
	public boolean hasNext() {
		return i.hasNext();
	}

	@Override
	public Presentation next() {
		return (Presentation)i.next();
	}

	@Override
	public void remove() {

	}

	
}
