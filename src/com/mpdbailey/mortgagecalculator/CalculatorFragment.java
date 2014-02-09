package com.mpdbailey.mortgagecalculator;

import com.pigdogbay.androidutils.usercontrols.NumberPicker;
import com.pigdogbay.androidutils.usercontrols.NumberPickerValue;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalculatorFragment extends Fragment{
	MortgagePresenter _MortgagePresenter;
	MortgageModel _MortgageModel;
	NumberPicker _MortgageNumberPicker; 
	
	public void setMortgageModel(MortgageModel model){
		_MortgageModel = model;
	}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	_MortgageNumberPicker = (NumberPicker)getActivity().findViewById(R.id.CalculatorMortgagePicker);
    	NumberPickerValue npv = new NumberPickerValue();
    	npv.setValue(_MortgageModel.Mortgage);
    	_MortgageNumberPicker.setNumberPickerValue(npv);
    }
}
