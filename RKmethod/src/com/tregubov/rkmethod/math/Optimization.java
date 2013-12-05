package com.tregubov.rkmethod.math;

public class Optimization {
	private static final double T = (Math.sqrt(5) + 1) / 2;

	public static double GoldenSection (Function1 f, double start, double end, double accuracy) {
		double a = start, b = end;
		double x1 = a + (b - a) / (T * T),
				x2 = a + (b - a) / T;

		while ((b - a) / 2 > accuracy) {
			if (f.f(x1) <= f.f(x2)){
				b = x2;
				x2 = x1;
				x1 = a + b - x2;
			}
			else {
				a = x1;
				x1 = x2;
				x2 = a + b - x1;
			}
		}
		return (a+b)/2;
	}

}
