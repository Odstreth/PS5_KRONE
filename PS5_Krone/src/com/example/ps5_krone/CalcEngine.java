package com.example.ps5_krone;

public class CalcEngine {

	public static double calc(double investmentAmount, double years, double annualInterestRate){
		double poweredAmount = Math.pow((double)1+ annualInterestRate/(double)100, years);
		return investmentAmount*poweredAmount;
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
