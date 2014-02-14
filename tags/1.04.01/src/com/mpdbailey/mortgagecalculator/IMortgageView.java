package com.mpdbailey.mortgagecalculator;

public interface IMortgageView
{
	String getMortgage();
	void setMortgage(String mortgage);

	String getPeriod();
	void setPeriod(String period);
	
	String getRate();
	void setRate(String rate);

	void setRepayment(String repayment);
}
