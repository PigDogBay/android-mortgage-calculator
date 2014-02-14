package com.mpdbailey.mortgagecalculator;

import com.pigdogbay.androidutils.usercontrols.INumberPickerValue;
import com.pigdogbay.androidutils.usercontrols.IValueChangedCallback;

public abstract class NumberPickerValueBase implements INumberPickerValue{

	protected double _Step = 1;
	protected double _Value=0;
	public IValueChangedCallback Callback;

	@Override
	public void increase() {
		_Value+=_Step;
		validate();
		onValueChanged();
	}

	@Override
	public void decrease() {
		_Value-=_Step;
		validate();
		onValueChanged();
	}

	@Override
	public abstract String getFormattedString();

	@Override
	public double getValue() {
		return _Value;
	}

	@Override
	public void setValue(double value) {
		_Value = value;
		validate();
		onValueChanged();
	}
	
	protected void validate(){
		if (_Value<0){
			_Value=0;
		}
	}
	protected void onValueChanged(){
		if (Callback!=null){
			Callback.ValueChanged(this);
		}
	}

}
