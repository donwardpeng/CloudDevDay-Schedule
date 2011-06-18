package com.clouddevday;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/*
 * The ScheduleAdapter Class extends the ArrayAdapter and is 
 * a custom Adapter used by the ScheduleActivity class.
 * 
 * @author Don Ward
 */
public class ScheduleAdapter extends ArrayAdapter<String> {
    Activity context;
    String[] items;

    public ScheduleAdapter(Activity aContext, String[] items) {
            super(aContext, R.layout.list_item, items);
            context = aContext;
            this.items = items;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

            View row = convertView;
            if (row == null){
            LayoutInflater inflater = (LayoutInflater) context.getLayoutInflater();
            row = inflater.inflate(R.layout.list_item, null);
            }
            TextView text = (TextView) row.findViewById(R.id.text1);

  
            String rowText = text.getText().toString();
         	text.setText(items[position]);
           
            return row;
    }
}