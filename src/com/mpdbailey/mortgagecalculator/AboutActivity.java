package com.mpdbailey.mortgagecalculator;

import com.mpdbailey.utils.ActivityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AboutActivity extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		RegisterIcon(R.id.facebookImg, R.string.facebookPage);
		RegisterIcon(R.id.twitterImg, R.string.twitter);
		RegisterIcon(R.id.androidImg, R.string.market);
		RegisterIcon(R.id.bloggerImg, R.string.blogger);
		RegisterIcon(R.id.pigdogbayImg, R.string.market);
	}

	private void RegisterIcon(int iconID, final int stringID)
	{
		ImageView img = (ImageView) findViewById(iconID);
		img.setOnClickListener(new View.OnClickListener()
		{
			public void onClick(View v)
			{
				ShowWebPage(getString(stringID));
			}
		});
	}

	private void ShowWebPage(String url)
	{
		try
		{
			ActivityUtils.ShowWebPage(this, url);
		} catch (Exception e){}
	}


}
