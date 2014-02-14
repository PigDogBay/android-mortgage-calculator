package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;
import java.text.ParseException;

public class MortgagePresenter
{
	private IMortgageView _View;
	
	public MortgagePresenter(IMortgageView view)
	{
		_View=view;
	}
	
	public void Initialize()
	{
		_View.setMortgage("");
		_View.setPeriod("");
		_View.setRate("");
		_View.setRepayment("");
	}
	
	public void Calculate()
	{
		double mortgage=0,rate=0,period=0;
		NumberFormat numFormat = NumberFormat.getNumberInstance();
		try
		{
			mortgage = numFormat.parse(_View.getMortgage()).doubleValue();
			rate = numFormat.parse(_View.getRate()).doubleValue()/100;
			period = numFormat.parse(_View.getPeriod()).doubleValue();
			double repayment = Calculate(mortgage, period, rate);
			_View.setRepayment(NumberFormat.getCurrencyInstance().format(repayment));
		}
		catch (ParseException ex)
		{
			_View.setRepayment("");
		}
	}
	/*
	Inputs: 
	mortgage - total mortgage amount
	rate - annual interest rate	
	period - number of years to repay

	returns the monthly repayment
	*/
	private double Calculate(double mortgage,double period, double rate) 
	{
	    if (mortgage == 0 || period == 0 || rate == 0) { return 0D; }
	    return (rate * mortgage / 12) / (1 - Math.pow((1 + rate), -period));
	}	
}
