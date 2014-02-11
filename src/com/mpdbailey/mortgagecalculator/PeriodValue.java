package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;

import com.pigdogbay.androidutils.usercontrols.INumberPickerValue;

public class PeriodValue implements INumberPickerValue{
	
	public double Step = 1;
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
		return NumberFormat.getIntegerInstance().format(_Value)+" years";
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
