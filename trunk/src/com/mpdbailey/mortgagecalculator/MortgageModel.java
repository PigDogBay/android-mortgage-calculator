package com.mpdbailey.mortgagecalculator;

public class MortgageModel {
	public double Mortgage;
	public double Rate;
	public double Period;
	public double getRepayment()
	{
	    if (Mortgage == 0 || Period == 0 || Rate == 0) { return 0D; }
	    return (Rate * Mortgage / 12D) / (1 - Math.pow((1D + Rate/12D), -Period*12D));
	}	
}
