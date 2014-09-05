package com.mpdbailey.mortgagecalculator;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

public class MortgageApplication extends Application{
	private static final String CATEGORY = "Mortgage Calculator";
	private static final String PREFIX = "MC ";
	private Tracker _Tracker;

	synchronized Tracker getTracker(){
		if (_Tracker==null){
			_Tracker = GoogleAnalytics.getInstance(this).newTracker(R.xml.app_tracker);
		}
		return _Tracker;
	}
	public static void trackEvent(Activity activity, String category, String action, String label){
		Tracker t = ((MortgageApplication)activity.getApplication()).getTracker();
    	t.send(new HitBuilders.EventBuilder()
		.setCategory(category)
		.setAction(action)
		.setLabel(label)
		.build());
	}
	public static void trackEvent(Activity activity, String screenName, String label){
		trackEvent(activity,CATEGORY, PREFIX+screenName, PREFIX+label);
	}	
	public static void trackEvent(Fragment fragment, String screenName, String label){
		trackEvent(fragment.getActivity(), screenName, label);
	}	

}
