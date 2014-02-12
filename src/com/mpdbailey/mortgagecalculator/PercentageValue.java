package com.mpdbailey.mortgagecalculator;

public class PercentageValue extends NumberPickerValueBase{

	public PercentageValue(){
		_Step = 0.001D;
	}

	@Override
	public String getFormattedString() {
		return String.format("%.2f%%", _Value*100D);
	}

}
