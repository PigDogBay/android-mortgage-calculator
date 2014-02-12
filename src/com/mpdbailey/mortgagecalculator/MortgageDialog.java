package com.mpdbailey.mortgagecalculator;

import java.util.Locale;

import android.content.Context;

import com.pigdogbay.androidutils.usercontrols.DialogBase;
import com.pigdogbay.androidutils.usercontrols.INumberPickerValue;

public class MortgageDialog extends DialogBase{

	public MortgageDialog(Context context, String hint, String title) {
		super(context, hint, title);
	}

	@Override
	protected String getValue(INumberPickerValue numberPickerValue) {
		double value = numberPickerValue.getValue();
		return String.format(Locale.US, "%.0f",value);
	}

	@Override
	protected void setValue(INumberPickerValue numberPickerValue, String value) {
		numberPickerValue.setValue(Double.parseDouble(value));
	}
}
