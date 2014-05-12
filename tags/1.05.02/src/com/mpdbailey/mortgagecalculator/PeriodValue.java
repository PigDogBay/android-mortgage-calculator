package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;

public class PeriodValue extends NumberPickerValueBase{

	public PeriodValue(){
		_Step = 1D;
	}

	@Override
	public String getFormattedString() {
		return NumberFormat.getIntegerInstance().format(_Value)+" years";
	}

}
