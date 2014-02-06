package com.mpdbailey.mortgagecalculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends Activity implements IMortgageView, OnClickListener, OnEditorActionListener{

	EditText editMortgage, editPeriod, editRate;
	TextView textRepayment;
	MortgagePresenter presenter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editMortgage = (EditText)findViewById(R.id.editMortage);
        editPeriod = (EditText)findViewById(R.id.editPeriod);
        editRate = (EditText)findViewById(R.id.editRate);
        textRepayment = (TextView) findViewById(R.id.textRepayment);
        editMortgage.setOnEditorActionListener(this);
        editPeriod.setOnEditorActionListener(this);
        editRate.setOnEditorActionListener(this);
        ((Button)findViewById(R.id.btnCalculate)).setOnClickListener(this);
        
        presenter = new MortgagePresenter(this);
        presenter.Initialize();
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

	public String getMortgage()
	{
		return this.editMortgage.getText().toString();
	}

	public void setMortgage(String mortgage)
	{
		this.editMortgage.setText(mortgage);	
	}

	public String getPeriod()
	{
		return this.editPeriod.getText().toString();
	}

	public void setPeriod(String period)
	{
		this.editPeriod.setText(period);	
	}

	public String getRate()
	{
		return this.editRate.getText().toString();
	}

	public void setRate(String rate)
	{
		this.editRate.setText(rate);	
	}

	public void setRepayment(String repayment)
	{
		this.textRepayment.setText(repayment);	
	}

	public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
	{
		presenter.Calculate();
		return false;
	}

	public void onClick(View v)
	{
		presenter.Calculate();
	}
}
