package com.clouddevday;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * The PresentationActivity class is used to display the Presentation Description in a WebView container.
 * 
 * @author Don Ward
 *
 */
public class PresentationActivity extends Activity {
	
	/**
	 * presentation_Description is used to retrieve the embedded HTML document for the WebView.
	 */
	private String presentation_Description;

	/**
	 * Called when the activity is first created. Retrieves the embedded HTML document
	 * name from the calling intent  and opens a WebView with it. 
	 * 
	 *  */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	

        //Read the extra info from the intent to get the Presentation Description text.
        Bundle extras = getIntent().getExtras();
        presentation_Description = extras.getString("presentation_Desc");       
       setContentView(R.layout.presentation_details);
	
       //Set up the WebView to view the presentation description
       WebView presentation_Text= (WebView)findViewById(R.id.webView_PresentationDetails);
       String presentation_URL = "file:///android_asset/" + presentation_Description + ".html";
       presentation_Text.loadUrl(presentation_URL);
	}
}