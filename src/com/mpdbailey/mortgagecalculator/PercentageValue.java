package com.mpdbailey.mortgagecalculator;

import com.pigdogbay.androidutils.usercontrols.INumberPickerValue;

public class PercentageValue implements INumberPickerValue{

	public double Step = 0.0025;
	double _Value=0;
	@Override
	public void increase() {
		_Value+=Step;
	}

	@Override
	public void decrease() {
		_Value-=Step;
		
	}

	@Override
	public String getFormattedString() {
		return String.format("%.2f%%", _Value*100D);
	}

	@Override
	public double getValue() {
		return _Value;
	}

	@Override
	public void setValue(double value) {
		_Value = value;
	}

}
