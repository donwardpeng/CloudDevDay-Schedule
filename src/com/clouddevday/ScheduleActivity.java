package com.clouddevday;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

/**
 * The Schedule Activity class extends ListActivity and displays the whole day schedule for the conference in a List Display.
 * @author Don Ward
 *
 */
public class ScheduleActivity extends ListActivity {
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       
        //Instantiate a Schedule Adapter
        ScheduleAdapter myListAdapter = new ScheduleAdapter(this,Startup.scheduleItems);
        setListAdapter(myListAdapter);
        ListView lv = getListView();
        lv.setCacheColorHint(0);
        lv.setTextFilterEnabled(true);
       
       
        //ListView On Click Handler
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //get the text of the selected list item and set the currentTab value for lookup on that tab
        	  String listItem = ((TextView)view.findViewById(R.id.text1)).getText().toString();
          	 if (listItem.contains("Time Slot 1"))
          	 {
             Intent intent = new Intent(view.getContext(), Startup.class);
            Startup.currentTab = 1;
             startActivity(intent);	 
          	 }          	 
          	 else if (listItem.contains("Time Slot 2"))
          	 {
             Intent intent = new Intent(view.getContext(), Startup.class);
             Startup.currentTab = 2;
             startActivity(intent);	 
          	 }          	 
          	 else if (listItem.contains("Time Slot 3"))
          	 {
             Intent intent = new Intent(view.getContext(), Startup.class);
             Startup.currentTab = 3;
             startActivity(intent);	 
          	 }          	 
          	 else if (listItem.contains("Time Slot 4"))
          	 {
             Intent intent = new Intent(view.getContext(), Startup.class);
             Startup.currentTab = 4;
            startActivity(intent);	 
          	 }          	 
          	 else if (listItem.contains("Time Slot 5"))
          	 {
             Intent intent = new Intent(view.getContext(), Startup.class);
             Startup.currentTab = 5;
             startActivity(intent);	 
          	 }
             else if(listItem.contains("Keynote"))
             {
                 Intent intent = new Intent(view.getContext(), PresentationActivity.class);
                 intent.putExtra("presentation_Desc","JineshVaria-PatternsandBestPractices");
                 startActivity(intent);
             }
             else if(listItem.contains("Cloud Patterns"))
             {
                 Intent intent = new Intent(view.getContext(), PresentationActivity.class);
                 intent.putExtra("presentation_Desc","BrianPrince-CloudPatternsandScenarios");
                 startActivity(intent);
             }
             else if(listItem.contains("Panel"))
             {
                 Intent intent = new Intent(view.getContext(), PresentationActivity.class);
                 intent.putExtra("presentation_Desc","Panel");
                 startActivity(intent);
             }
          	        	 
          	 else 
        	 {
        		 return;
        	 }
         }
        });
	
	}
}
