package com.mpdbailey.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ActivityUtils
{
	public static void ShowWebPage(Activity currentActivity, String url)
	{
		Intent intent = new Intent();
		intent.setAction(Intent.ACTION_VIEW);
		intent.addCategory(Intent.CATEGORY_BROWSABLE);
		intent.setData(Uri.parse(url));
		currentActivity.startActivity(intent);
	}
}
