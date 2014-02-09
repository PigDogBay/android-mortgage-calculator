package com.mpdbailey.mortgagecalculator;

import com.pigdogbay.androidutils.mvp.BackgroundColorModel;
import com.pigdogbay.androidutils.mvp.BackgroundColorPresenter;
import com.pigdogbay.androidutils.mvp.IBackgroundColorView;
import com.pigdogbay.androidutils.usercontrols.NumberPicker;
import com.pigdogbay.androidutils.utils.ActivityUtils;
import com.pigdogbay.androidutils.utils.PreferencesHelper;
import com.pigdogbay.weightrecorder.R;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnSharedPreferenceChangeListener,IBackgroundColorView{

	EditText editMortgage, editPeriod, editRate;
	TextView textRepayment;
	BackgroundColorPresenter _BackgroundColorPresenter;
	private MortgageModel _MortgageModel;
	NumberPicker _MortgageNumberPicker; 

	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        AppPagerAdapter adapter = new AppPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager)findViewById(R.id.main_viewpager);
        viewPager.setAdapter(adapter);

        PreferencesHelper ph = new PreferencesHelper(this);
		BackgroundColorModel bcm = new BackgroundColorModel(ph);
		_BackgroundColorPresenter = new BackgroundColorPresenter(this,bcm);
		_BackgroundColorPresenter.updateBackground();
		
		_MortgageModel = createMortgageModel();
		checkAppRate();
    }
    private MortgageModel createMortgageModel(){
    	PreferencesHelper ph = new PreferencesHelper(this);
		MortgageModel mortgageModel = new MortgageModel();
		mortgageModel.Mortgage = ph.getDouble(R.string.code_pref_mortgage_amount_key, 100000D);
		mortgageModel.Period = ph.getDouble(R.string.code_pref_mortgage_period_key, 25D);
		mortgageModel.Rate = ph.getDouble(R.string.code_pref_mortgage_rate_key, 0.05D);
    	return mortgageModel;
    }
    private void saveData(MortgageModel mm){
    	PreferencesHelper ph = new PreferencesHelper(this);
    	ph.setDouble(R.string.code_pref_mortgage_amount_key, mm.Mortgage);
    	ph.setDouble(R.string.code_pref_mortgage_period_key, mm.Period);
    	ph.setDouble(R.string.code_pref_mortgage_rate_key, mm.Rate);
    	
    }
    @Override
    protected void onResume() {
    	super.onResume();
		PreferenceManager.getDefaultSharedPreferences(this)
		.registerOnSharedPreferenceChangeListener(this);
    }
    @Override
    protected void onPause() {
    	super.onPause();
		PreferenceManager.getDefaultSharedPreferences(this)
		.unregisterOnSharedPreferenceChangeListener(this);
		saveData(_MortgageModel);
    }
    @Override
    protected void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
		PreferenceManager.getDefaultSharedPreferences(this)
		.unregisterOnSharedPreferenceChangeListener(this);
    }
    
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Intent intent;
		switch (item.getItemId())
		{
		case (R.id.menu_about):
			intent = new Intent(this, AboutActivity.class);
			startActivity(intent);
			break;
		case (R.id.menu_share):
			shareMortgageDetails();
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
		if (key.equals(getString(R.string.code_pref_background_colour))){
			_BackgroundColorPresenter.updateBackground();
		}
	}	

	@Override
	public void setBackgroundColor(int id) {
		ActivityUtils.setBackground(this, R.id.rootLayout, id);
	}
	
	private void checkAppRate() {
		new com.pigdogbay.androidutils.apprate.AppRate(this)
				.setMinDaysUntilPrompt(7).setMinLaunchesUntilPrompt(5)
				.init();
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
                    return new SettingsFragment();
                case 2:
                    return new HelpFragment();
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
                    return "SETTINGS";
                case 2:
                    return "HELP";
            }
            return null;
        }
    }
	
    private void shareMortgageDetails(){
    	ActivityUtils.shareText(this, "Mortgage Details", "�500 per month", R.string.chooser_mortgage_share);
    }
	
}
