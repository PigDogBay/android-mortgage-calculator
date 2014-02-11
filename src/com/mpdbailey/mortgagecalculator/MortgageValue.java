package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;

import com.pigdogbay.androidutils.usercontrols.INumberPickerValue;

public class MortgageValue implements INumberPickerValue{

	public double Step = 1000;
	private double _Value;
	@Override
	public void increase() {
		_Value+=Step;
		validate();
	}

	@Override
	public void decrease() {
		_Value-=Step;
		validate();
	}

	@Override
	public String getFormattedString() {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(_Value);
	}

	@Override
	public double getValue() {
		return _Value;
	}

	@Override
	public void setValue(double value) {
		_Value = value;	
		validate();
	}
	
	private void validate(){
		if (_Value<0){
			_Value = 0;
		}
	}

}
