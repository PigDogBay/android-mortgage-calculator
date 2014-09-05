package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.pigdogbay.androidutils.mvp.BackgroundColorModel;
import com.pigdogbay.androidutils.mvp.BackgroundColorPresenter;
import com.pigdogbay.androidutils.mvp.IBackgroundColorView;
import com.pigdogbay.androidutils.utils.ActivityUtils;
import com.pigdogbay.androidutils.utils.PreferencesHelper;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements
		OnSharedPreferenceChangeListener, IBackgroundColorView {

	EditText editMortgage, editPeriod, editRate;
	TextView textRepayment;
	BackgroundColorPresenter _BackgroundColorPresenter;
	private static MortgageModel _MortgageModel;
	private AdView _AdView;

	private static final String MAIN_MENU_CATEGORY = "Main Menu";
	

	public static MortgageModel getMortgageModel(Context context) {
		if (_MortgageModel == null) {
			PreferencesHelper ph = new PreferencesHelper(context);
			_MortgageModel = new MortgageModel();
			_MortgageModel.Mortgage = ph.getDouble(
					R.string.code_pref_mortgage_amount_key, 100000D);
			_MortgageModel.Period = ph.getDouble(
					R.string.code_pref_mortgage_period_key, 25D);
			_MortgageModel.Rate = ph.getDouble(
					R.string.code_pref_mortgage_rate_key, 0.05D);
		}
		return _MortgageModel;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		AppPagerAdapter adapter = new AppPagerAdapter(
				getSupportFragmentManager());
		ViewPager viewPager = (ViewPager) findViewById(R.id.main_viewpager);
		viewPager.setAdapter(adapter);

		PreferencesHelper ph = new PreferencesHelper(this);
		BackgroundColorModel bcm = new BackgroundColorModel(ph);
		_BackgroundColorPresenter = new BackgroundColorPresenter(this, bcm);
		_BackgroundColorPresenter.updateBackground();
		setupAds();

		//Get tracker - hopefully will auto track the activities
		((MortgageApplication) getApplication()).getTracker();

		checkAppRate();
	}

	private void setupAds() {
		// Look up the AdView as a resource and load a request.
		_AdView = (AdView) this.findViewById(R.id.adView);
		AdRequest adRequest = new AdRequest.Builder()
				.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
				.addTestDevice(getString(R.string.code_test_device_1_id))
				.addTestDevice(getString(R.string.code_test_device_2_id))
				.addTestDevice(getString(R.string.code_test_device_3_id))
				.build();
		_AdView.loadAd(adRequest);
	}

	private void saveData(MortgageModel mm) {
		PreferencesHelper ph = new PreferencesHelper(this);
		ph.setDouble(R.string.code_pref_mortgage_amount_key, mm.Mortgage);
		ph.setDouble(R.string.code_pref_mortgage_period_key, mm.Period);
		ph.setDouble(R.string.code_pref_mortgage_rate_key, mm.Rate);

	}

	@Override
	protected void onResume() {
		super.onResume();
		_AdView.resume();
		PreferenceManager.getDefaultSharedPreferences(this)
				.registerOnSharedPreferenceChangeListener(this);
	}

	@Override
	protected void onPause() {
		_AdView.pause();
		super.onPause();
		PreferenceManager.getDefaultSharedPreferences(this)
				.unregisterOnSharedPreferenceChangeListener(this);
		saveData(getMortgageModel(this));
	}

	@Override
	protected void onDestroy() {
		_AdView.destroy();
		super.onDestroy();
		PreferenceManager.getDefaultSharedPreferences(this)
				.unregisterOnSharedPreferenceChangeListener(this);
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
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent intent;
		switch (item.getItemId()) {
		case (R.id.menu_about):
			intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			break;
		case (R.id.menu_share):
			shareMortgageDetails();
			MortgageApplication.trackEvent(this, MAIN_MENU_CATEGORY, "share");
			break;
		default:
			return false;
		}
		return true;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
			String key) {
		if (key.equals(getString(R.string.code_pref_background_colour))) {
			_BackgroundColorPresenter.updateBackground();
		}
	}

	@Override
	public void setBackgroundColor(int id) {
		ActivityUtils.setBackground(this, R.id.rootLayout, id);
	}

	private void checkAppRate() {
		new com.pigdogbay.androidutils.apprate.AppRate(this)
				.setMinDaysUntilPrompt(7).setMinLaunchesUntilPrompt(5).init();
	}

	public class AppPagerAdapter extends FragmentPagerAdapter {

		public AppPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new CalculatorFragment();
			case 1:
				return new HelpFragment();
			case 2:
				return new SettingsFragment();
			}
			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			switch (position) {
			case 0:
				return "CALCULATOR";
			case 1:
				return "HELP";
			case 2:
				return "SETTINGS";
			}
			return null;
		}
	}

	private void shareMortgageDetails() {
		String template = Html
				.fromHtml(this.getString(R.string.share_template)).toString();
		template = template.replace("$mortgage", NumberFormat
				.getCurrencyInstance().format(_MortgageModel.Mortgage));
		template = template.replace("$period", NumberFormat
				.getIntegerInstance().format(_MortgageModel.Period) + " years");
		template = template.replace("$rate",
				String.format("%.2f%%", _MortgageModel.Rate * 100D));
		template = template.replace("$monthlyrepayment", NumberFormat
				.getCurrencyInstance().format(_MortgageModel.getRepayment()));
		ActivityUtils.shareText(this, "Mortgage Details", template,
				R.string.chooser_mortgage_share);
	}

}
