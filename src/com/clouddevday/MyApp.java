package com.clouddevday;

import android.content.Context;

/*
 * The MyApp Class is used to return a Application context for use in Classes that do not have access 
 * to the application context. It needs to be instantiated within an Activity or Class that has access to the 
 * application context. 
 * 
 * @author Don Ward
 */
public class MyApp{
	
	private static Context contextInstance;
    public MyApp(Context theAppContext) {
        contextInstance = theAppContext;
    }
    
    /**
	 * getContext - used to return the Application context
	 * 
	 *@return Context applicationContext
	 */
    public static Context getContext() {
        return contextInstance;
    }
}

