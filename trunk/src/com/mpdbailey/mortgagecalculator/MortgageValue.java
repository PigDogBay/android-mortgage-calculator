package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;

public class MortgageValue extends NumberPickerValueBase{

	public MortgageValue(){
		_Step = 1000D;
	}
	@Override
	public String getFormattedString() {
		NumberFormat format = NumberFormat.getCurrencyInstance();
		return format.format(_Value);
	}

}
