package com.mpdbailey.mortgagecalculator;

import java.text.NumberFormat;

import com.pigdogbay.androidutils.usercontrols.INumberPickerValue;
import com.pigdogbay.androidutils.usercontrols.IValueChangedCallback;
import com.pigdogbay.androidutils.usercontrols.NumberPicker;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CalculatorFragment extends Fragment implements IValueChangedCallback{
	MortgagePresenter _MortgagePresenter;
	MortgageModel _MortgageModel;
	
	NumberPickerValueBase _MortgageValue, _RateValue, _PeriodValue;
	TextView _ResultTextView;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_calculator, container, false);
        return rootView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
    	// TODO Auto-generated method stub
    	super.onActivityCreated(savedInstanceState);
    	_MortgageModel = MainActivity.getMortgageModel(this.getActivity());
    	
    	_ResultTextView = (TextView)getActivity().findViewById(R.id.CalculatorRepaymentTextView);
    	
    	//Set up number pickers
    	createValues();
    	modelToView();
    	NumberPicker numPicker = (NumberPicker)getActivity().findViewById(R.id.CalculatorMortgagePicker);
    	numPicker.setNumberPickerValue(_MortgageValue);
    	numPicker = (NumberPicker)getActivity().findViewById(R.id.CalculatorRatePicker);
    	numPicker.setNumberPickerValue(_RateValue);
    	numPicker.setNumberEditorDialog(new PercentDialogue(getActivity(),"Percentage", "Enter Rate"));
    	numPicker = (NumberPicker)getActivity().findViewById(R.id.CalculatorPeriodPicker);
    	numPicker.setNumberPickerValue(_PeriodValue);
    	numPicker.setNumberEditorDialog(new PeriodDialog(getActivity(),"Years", "Enter Period In Years"));
    }
    @Override
    public void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	attachValueChangeListener();
    }
    @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	detachValueChangeListener();
    }
    @Override
    public void onDestroy() {
    	// TODO Auto-generated method stub
    	super.onDestroy();
    	detachValueChangeListener();
    }
    private void createValues()
    {
    	_MortgageValue = new MortgageValue();
    	_RateValue = new PercentageValue();
    	_PeriodValue = new PeriodValue();
    	
    }
    private void modelToView(){
    	_MortgageValue.setValue(_MortgageModel.Mortgage);
    	_RateValue.setValue(_MortgageModel.Rate);
    	_PeriodValue.setValue(_MortgageModel.Period);
    	setResult();
    }
    private void viewToModel(){
    	_MortgageModel.Mortgage = _MortgageValue.getValue();
    	_MortgageModel.Rate = _RateValue.getValue();
    	_MortgageModel.Period = _PeriodValue.getValue();
    	
    }
    private void setResult(){
    	String result = NumberFormat.getCurrencyInstance().format(_MortgageModel.getRepayment());
    	_ResultTextView.setText(result);
    }
    private void attachValueChangeListener(){
    	_MortgageValue.Callback = this;
    	_RateValue.Callback = this;
    	_PeriodValue.Callback = this;
    }
    private void detachValueChangeListener(){
    	_MortgageValue.Callback = null;
    	_RateValue.Callback = null;
    	_PeriodValue.Callback = null;
    }
    
	@Override
	public void ValueChanged(INumberPickerValue sender) {
		viewToModel();
		setResult();
	}
}
