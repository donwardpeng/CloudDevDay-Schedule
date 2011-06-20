package com.clouddevday;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
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

	/**
	 * The splashThread member variable is used to pause the application while
	 * the splash screen displays. The splash screen either times out or the
	 * user taps the screen and interrupts the thread.
	 */
	private Thread splashThread;
	private static final int MAX_PROGRESS = 100;
	/**
	 * mProgressHandler is used to handle the loading of remote data while the
	 * Progress Dialog is displayed
	 */
	private Handler mProgressHandler;
	private int mProgress;
	private ProgressDialog mProgressDialog;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		MyApp app = new MyApp(this.getApplicationContext());
		// //Read the Schedule data from the local string resource.
		// Startup.timeRoomsPresenters =
		// getResources().getStringArray(R.array.time_room_presenter_array);

		/*
		 * define the mProgressHandler
		 */
		mProgressHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (mProgress >= MAX_PROGRESS) {
					mProgressDialog.dismiss();
				} else {
					mProgress = MAX_PROGRESS;
					ScheduleDataManager scheduleDM = new ScheduleDataManager();

					int remoteVersionNumber;
					if (scheduleDM.checkForAndParseRemoteData())
					/*Remote Data Exists*/
					{					
					if ((remoteVersionNumber = scheduleDM.getRemoteVersionNumber()) != 0) {
						/*If remote version number is not zero*/
						if (scheduleDM.localDataFileExists()) {
							if (scheduleDM.getLocalVersionNumber() == remoteVersionNumber) {
								scheduleDM.readLocalDataFile();
							}/*end local version number equals remote version number */
							else if (scheduleDM.readRemoteData()) {
								scheduleDM.storeRemoteDatatoDataFile();
							}/*end if for read Remote Data */

						}/*end localDataFileExists*/
						else {
							scheduleDM.readLocalResourceStringData();
						}/*end else*/

					}/*end remote version number is not zero */
				}/*end check if Remote Data Exists*/
					else
					{
						if (scheduleDM.localDataFileExists()) {
							if (scheduleDM.getLocalVersionNumber() > 0) {
								scheduleDM.readLocalDataFile();
							}/*end local version number > 0 */
						}/*end localDataFileExists*/
						else {
							scheduleDM.readLocalResourceStringData();
						}/*end else*/
						
					}/*end else*/
					Startup.timeRoomsPresenters = scheduleDM.getTimeRoomsPresenters();
					mProgressHandler.sendEmptyMessageDelayed(0, 100);
				}
			}
		};
		/*
		 * Set up the Load Progress Dialog and display it.
		 */
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setTitle("Loading Data.");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMax(MAX_PROGRESS);
		mProgress = 0;
		mProgressDialog.setProgress(0);
		mProgressDialog.show();

		mProgressHandler.sendEmptyMessage(0);
		mProgressDialog.getWindow().setGravity(Gravity.LEFT);

		// instantiate the splashThread
		splashThread = new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					synchronized (this) {

						wait(2000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				finish();
				Intent intent = new Intent();
				intent.setClassName("com.clouddevday",
						"com.clouddevday.Startup");
				startActivity(intent);
				stop();
			}
		};
		// Start the splashThread
		splashThread.start();
	}

	/**
	 * onTouchEvent will notify the spashThread which in turn will load the set
	 * the intent for the Startup Activity and starts it. (non-Javadoc)
	 * 
	 * @see android.app.Activity#onTouchEvent(android.view.MotionEvent)
	 */
//	@Override
//	public boolean onTouchEvent(MotionEvent evt) {
//		if (evt.getAction() == MotionEvent.ACTION_DOWN) {
//			synchronized (splashThread) {
//				splashThread.notifyAll();
//			}
//		}
//		return true;
//	}
}
