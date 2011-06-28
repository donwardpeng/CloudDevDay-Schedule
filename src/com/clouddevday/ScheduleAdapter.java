package com.clouddevday;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * The ScheduleAdapter Class extends the ArrayAdapter and is 
 * a custom Adapter used by the ScheduleActivity class. By implementing 
 * a custom Adapter the List can be manipulated in future version (i.e.
 * highlighting list items with different colors)
 * 
 * @author Don Ward
 */
public class ScheduleAdapter extends ArrayAdapter<String> {
    /**
     * context to the Activity
     */
	Activity context;
	/**
	 * items to add to the ArrayAdapter
	 */
    String[] items;

    /**
     * Constructor to setup the Adapter
     * @param aContext
     * @param items
     */
    public ScheduleAdapter(Activity aContext, String[] items) {
            super(aContext, R.layout.list_item, items);
            context = aContext;
            this.items = items;
    }

    /**
     * Custom getView method to handle updates to the Listview. 
     */
    public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getLayoutInflater();
            row = inflater.inflate(R.layout.list_item, null);
            }
            TextView text = (TextView) row.findViewById(R.id.text1);
         	text.setText(items[position]);
            return row;
    }
}