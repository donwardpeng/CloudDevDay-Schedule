package com.clouddevday;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
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
        
        //create new AnimationSet
        AnimationSet set = new AnimationSet(true);

        //define a new AlphaAnimation
        Animation animation = new AlphaAnimation(0.0f, 1.0f);
        //set the duration to 50ms
        animation.setDuration(50);
        //add the animation to the AnimationSet
        set.addAnimation(animation);

        //define a new TranslateAnimation
        animation = new TranslateAnimation(
            Animation.RELATIVE_TO_SELF, 0.0f,Animation.RELATIVE_TO_SELF, 0.0f,
            Animation.RELATIVE_TO_SELF, -1.0f,Animation.RELATIVE_TO_SELF, 0.0f
        );
        //set the duration to 100ms
        animation.setDuration(100);
        //add the animation to the AnimationSet
        set.addAnimation(animation);
        
        //define a new LayoutAnimationController
        LayoutAnimationController controller = new LayoutAnimationController(set, 0.5f);
        ListView lv = getListView();
        
        //set the ListView animation
        lv.setLayoutAnimation(controller);
  
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
            		AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
              		builder.setMessage(listItem.toString() + "\n\nNote: Items labelled with ** link to presentation information.")
              		.setCancelable(false)
              		.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
              			public void onClick(DialogInterface dialog, int id) {
                   // ScheduleActivity.this.;
              			}
              		});
              		AlertDialog alert = builder.create();
              		alert.show();
        		 return;
        	 }
         }
        });
	
	}
}
