package com.mpdbailey.mortgagecalculator;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.pigdogbay.androidutils.mvp.BackgroundColorModel;
import com.pigdogbay.androidutils.mvp.BackgroundColorPresenter;
import com.pigdogbay.androidutils.mvp.IBackgroundColorView;
import com.pigdogbay.androidutils.utils.ActivityUtils;
import com.pigdogbay.androidutils.utils.PreferencesHelper;


public class AboutActivity extends Activity implements IBackgroundColorView
{
	private static final String EVENT_LABEL = "about";
	BackgroundColorPresenter _BackgroundColorPresenter;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);

		DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels/2;
		
        Button btn = (Button) findViewById(R.id.aboutBtnRate);
        btn.setMinimumWidth(width);
        btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v)
			{
				showWebPage(R.string.market_url);
				MortgageApplication.trackEvent(AboutActivity.this, EVENT_LABEL, "rate");
			}
		});
        btn = (Button) findViewById(R.id.aboutBtnFacebook);
        btn.setMinimumWidth(width);
        btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWebPage(R.string.market_pigdogbay_apps);
				MortgageApplication.trackEvent(AboutActivity.this, EVENT_LABEL, "get_more_apps");
			}
		});
		((Button) findViewById(R.id.aboutBtnLegal)).setOnClickListener(new OnClickListener()
		{
			public void onClick(View v)
			{
				showLegal();
				MortgageApplication.trackEvent(AboutActivity.this, EVENT_LABEL, "legal");
			}
		});
        findViewById(R.id.aboutFacebookLink).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWebPage(R.string.facebookPage);
		    	MortgageApplication.trackEvent(AboutActivity.this, EVENT_LABEL, "facebook");
			}
		});
        findViewById(R.id.aboutTwitterLink).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWebPage(R.string.twitter);
		    	MortgageApplication.trackEvent(AboutActivity.this, EVENT_LABEL, "twitter");
			}
		});
        findViewById(R.id.aboutWebsiteLink).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				showWebPage(R.string.website);
		    	MortgageApplication.trackEvent(AboutActivity.this, EVENT_LABEL, "website");
			}
		});
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		PreferencesHelper ph = new PreferencesHelper(this);
		BackgroundColorModel bcm = new BackgroundColorModel(ph);
		_BackgroundColorPresenter = new BackgroundColorPresenter(this,bcm);
		_BackgroundColorPresenter.updateBackground();
		
	}
	@Override
	protected void onStart() {
		super.onStart();
		//Report the start of an Activity, so that it can be tracked by any Trackers that have enabled auto activity tracking
		GoogleAnalytics.getInstance(this).reportActivityStart(this);
	}
	@Override
	protected void onStop() {
		super.onStop();
		GoogleAnalytics.getInstance(this).reportActivityStop(this);;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			finish();
			break;
		}
		return true;
	}	

	private void showWebPage(int urlId)
	{
		try
		{
			ActivityUtils.ShowWebPage(this, getString(urlId));
		}
		catch (ActivityNotFoundException e)
		{ 
				Toast.makeText(this, getString(R.string.about_no_market_app), Toast.LENGTH_LONG)
					.show();
		}
		
	}
	private void showLegal()
	{
		ActivityUtils.showInfoDialog(this, R.string.copyright_title, R.string.copyright);
	}
	@Override
	public void setBackgroundColor(int id) {
		ActivityUtils.setBackground(this, R.id.rootLayout, id);
	}
}
