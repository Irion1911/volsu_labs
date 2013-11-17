package com.tregubov.rkmethod.math;

public abstract class Function1 {
	
	public double length(double a, double b){
		return Math.sqrt((f(a) - f(b))*(f(a) - f(b)) + (a-b)*(a-b));
	}
	
	public double length(double a, double b, double infelicity){
		double h = (b-a);//*infelicity;
		double s = 0;
		for(double i = a; i < b; i+=h)
			s += length(i, i+h);
		return s;
	}

	public abstract double f(double x);
}
