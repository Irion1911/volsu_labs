package com.tregubov.rkmethod.math;


public class Spline {
	
	// Функция вида y = F(x);
	private Function mF;
	
	public Spline(Function f){
		mF = f;
	}
	
	public double length(double a, double b){
		return Math.sqrt((mF.calculate(a) - mF.calculate(b))*(mF.calculate(a) - mF.calculate(b)) + (a-b)*(a-b));
	}
	
	public double length(double a, double b, double infelicity){
		double h = (b-a)*infelicity, s = 0;
		for(double i = a; i < b; i+=h)
			s += length(i, i+h);
		return s;
	}
}
