package com.clouddevday;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

/**
 * The PresentationActivity class is used to display the Presentation Description in a WebView container.
 * It is not part of the Tabs.
 * 
 * @author Don Ward
 *
 */
public class PresentationActivity extends Activity {
	private String presentation_Description;

	/**Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);	

        //Read the extra from the intent to get the Presentation Description text.
        Bundle extras = getIntent().getExtras();
        presentation_Description = extras.getString("presentation_Desc");       
        //Surround the presentation description text with an html header and body
        
 //       presentation_Description = "<html><body style=\"background-color:#000000\"><font color=\"White\">" 
 //     	+ presentation_Description + "</body></html>";
       setContentView(R.layout.presentation_details);
	
       //Set up the webview to view the presentation description
       WebView presentation_Text= (WebView)findViewById(R.id.webView_PresentationDetails);
       String presentation_URL = "file:///android_asset/" + presentation_Description + ".html";
       presentation_Text.loadUrl(presentation_URL);
       
	
	}
}