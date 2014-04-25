package com.mpdbailey.mortgagecalculator;

import android.app.Application;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

public class MortgageApplication extends Application{
	private Tracker _Tracker;

	synchronized Tracker getTracker(){
		if (_Tracker==null){
			_Tracker = GoogleAnalytics.getInstance(this).newTracker(R.xml.app_tracker);
		}
		return _Tracker;
	}

}
