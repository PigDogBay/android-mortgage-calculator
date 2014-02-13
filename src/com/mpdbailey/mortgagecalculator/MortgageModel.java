package com.mpdbailey.mortgagecalculator;

public class MortgageModel {
	public double Mortgage;
	
	/**
	 * Annual interest rate (as decimal)
	 */
	public double Rate;
	
	/**
	 * Period in years 
	 */
	public double Period;
	
	/**
	 * See http://en.wikipedia.org/wiki/Mortgage_calculator
	 * Please note, the BBC mortgage calculator has a bug
	 * @return monthly repayment
	 */
	public double getRepayment()
	{
	    if (Mortgage == 0 || Period == 0 || Rate == 0) { return 0D; }
	    return (Rate * Mortgage / 12D) / (1 - Math.pow((1D + Rate/12D), -Period*12D));
	}	
	
	/**
	 * Calculates the the monthly charge for an interest only mortgage
	 * @return interest only payment
	 */
	public double getInterestOnly(){
		return Mortgage*Rate/12D;
	}
	
}
