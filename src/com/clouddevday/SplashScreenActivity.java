package com.clouddevday;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;

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
	 * the splash screen displays. The splash screen will time out after 2 seconds.
	 */
	private Thread splashThread;
	/**
	 * Maximum progress for the Progress Dialog
	 */
	private static final int MAX_PROGRESS = 100;
	/**
	 * Log TAG
	 */
	private static final String TAG = "SplashScreenActivity";
	/**
	 * mProgressHandler is used to handle the loading of remote data while the
	 * Progress Dialog is displayed
	 */
	private Handler mProgressHandler;
	private int mProgress;
	private ProgressDialog mProgressDialog;
	@SuppressWarnings("unused")
	private MyApp app;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splashscreen);
		app = new MyApp(this.getApplicationContext());
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
		Log.i(TAG, "Setting up Progress Dialog");
		mProgressDialog = new ProgressDialog(this);
		mProgressDialog.setTitle("Loading Data.");
		mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		mProgressDialog.setMax(MAX_PROGRESS);
		mProgress = 0;
		mProgressDialog.setProgress(0);
		mProgressDialog.show();
		Log.i(TAG, "Sending Empty Message to Progress Dialog.");
		mProgressHandler.sendEmptyMessage(0);
		mProgressDialog.getWindow().setGravity(Gravity.LEFT);
		Log.i(TAG, "Entering Thread.");		
		// instantiate the splashThread
		splashThread = new Thread() {
			@Override
			public void run() {
				super.run();
				try {
					synchronized (this) {
						Log.i(TAG, "In Thread - Waiting 2 seconds.");
						wait(2000);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Log.i(TAG, "In Thread - About to finish Activity.");
				finish();
				Intent intent = new Intent();
				intent.setClassName("com.clouddevday",
						"com.clouddevday.Startup");
				startActivity(intent);
			//	stop();
			}
		};
		// Start the splashThread
		splashThread.start();
	}

}
