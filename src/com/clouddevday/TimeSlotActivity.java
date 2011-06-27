package com.clouddevday;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.ListActivity;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

/**
 * The TimeSlotActivity class extends ListActivity and is responsible for filling in the individual TimeSlot tabs
 * with the Room, Presenter and Presentation list items.
 *   
 *     
 * @author Don Ward
 */

public class TimeSlotActivity extends ListActivity {
private String timeSlotName;
	

/**Called when the activity is first created. */	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       //Get the Time Slot Name which is passed in the Intent Extras Bundle
        Bundle extras = getIntent().getExtras();
        timeSlotName = extras.getString("intentTimeSlotName");

        //Populate a Hashmap with the rooms being used for this time slot
        HashMap<String, String> roomData = new HashMap<String,String>();
        roomData = Startup.timeSlotMap.get(timeSlotName);
        
        //Iterate through all of the rooms for this time slot and 
        //get the presenter and presentation title
        Iterator<String> it =roomData.keySet().iterator();
        ArrayList<String> presenters = new ArrayList<String>();
        while (it.hasNext())
        {
        String key = (String)it.next();
        //Get the presenters name
        String value =  roomData.get(key);
        //Strip the number off the presenters name if one exists
        String[] values = value.split("\\d");
        //result = "Room Name - Presenter" 
        String result = "Room: " +  key + " \nPresenter:  " + values[0];
        //Get the Presentation title based on the original presenters name
        String name = Startup.presentations.getPresentationTitle(value);
        //if this is a empty string then do not append it
        if (!name.equals(""))
        //result = "Room Name - Presenter \n Presentation Name"
        result = result + "\n ** " + name + " **";
        //Add to the Presenters Array List
        presenters.add(result);
        }
        //Sort the Arraylist - sorting by Room Name primarily
        Collections.sort(presenters);
       
        //Construct a List containing HashMaps of <String,String> for use
        //in constructing the ListAdapter la
        List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        for (int i = 0; i < presenters.size(); i++)
        {
        	HashMap<String,String> hash = new HashMap<String,String>();
        	hash.put("line1", presenters.get(i));
        	list.add(hash);
        }
        
        //Set up the ListAdapter
        ListAdapter la = new SimpleAdapter(this, 
        		list, 
        		R.layout.list_item,
        		new String[]{"line1"},
        		new int[] {R.id.text1}); 
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
        
        //Add the ArrayList to the List of items
        setListAdapter(la);
        ListView lv = getListView();
        lv.setLayoutAnimation(controller);
        lv.setCacheColorHint(0);
        lv.setTextFilterEnabled(true);
        
        //On Click, lookup the presentation title and display the proper info on the 
        //presenter and presentation
        lv.setOnItemClickListener(new OnItemClickListener() {
          public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
          //get the text of the selected list item
        	 String listItem = ((TextView)view.findViewById(R.id.text1)).getText().toString();
        	 String s1[] = null;
        	 //if the text contains "*" then there exists a Presentation Title 
        	 if  (listItem.contains("*"))
        	 {
        	 s1 = listItem.split("\\*\\* ");
        	 }
        	 else
        	 {
        		 return;
        	 }
        	 String key;
        	//if the key (Presentation Title) is null, then do nothing
        	 if (s1 ==null || s1[0] == null)
        		 return;
        	 //else get the Presentation Title
        	 String[] s2 = s1[1].split(" \\*\\*");
        	 key = s2[0];
        	 //if the key is null, then do nothing
        	 if (key == null)
        		 return;
        	 String s3[] = null;
        	 String s4[] = null;
        	 if(listItem.contains("Presenter: "))
        	 {
        		 s3 = listItem.split("Presenter: ");
        	 }
        	 else{
        		 return;
        	 }
        	 s4 = s3[1].split("\n"); 
        	 //Parse out any special characters (space,:, and \) in the presentation_Desc
        	 //to be able to properly retrieve the correct HTML file
        	 String desc = s4[0] + "- " + key;
        	 String desc1 = desc.replace(" ", "");
        	 String desc2 = desc1.replace(":","");
        	 String desc3 = desc2.replace("\'","");
        	 // Create an Intent to launch an Activity for the tab (to be reused)
          Intent intent = new Intent(view.getContext(), PresentationActivity.class);
          intent.putExtra("presentation_Desc",desc3);
          startActivity(intent);
          }
        });
    }
	
}
