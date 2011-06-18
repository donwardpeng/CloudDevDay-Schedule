package com.clouddevday;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
/*
 * The SplashScreenActivity Class is used to display the initial Splash Screen for the application.
 * It also reads the Schedule data into the Startup class static member variable - timeRoomPresenters.
 * This data can either be read from a web-site using the readRemoteData method of the Startup Class or 
 * from the local string resource. 
 * 
 * @author Don Ward
 */
public class SplashScreenActivity extends Activity {

	/*
	 * The splashThread member variable is used to pause the application while the splash screen displays. The splash
	 * screen either times out or the user taps the screen and interrupts the thread.
	 */
	private Thread splashThread;
	
	/**Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		   super.onCreate(savedInstanceState);
		   setContentView(R.layout.splashscreen);

		   //Read the Schedule data from the local string resource.
		   Startup.timeRoomsPresenters = getResources().getStringArray(R.array.time_room_presenter_array);

		   //instantiate the splashThread
		  splashThread = new Thread()
		   {
			@Override
			public void run() {
			super.run();
		       try {
		    	   synchronized(this){
		    		   
					wait(3000);
					}
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				   finish();
				   Intent intent = new Intent();
				   intent.setClassName("com.clouddevday","com.clouddevday.Startup");
				   startActivity(intent);
				   stop();
				}   
		   };
		   //Start the splashThread
		   splashThread.start();
	   }
	/*
	 * onTouchEvent will notify the spashThread which in turn will load the set the intent for the
	 * Startup Activity and starts it.
	 * (non-Javadoc)
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
	   @Override
	    public boolean onTouchEvent(MotionEvent evt)
	    {
	        if(evt.getAction() == MotionEvent.ACTION_DOWN)
	        {
	            synchronized(splashThread){
	               splashThread.notifyAll();
	            }
	        }
	        return true;
	    }    
}
