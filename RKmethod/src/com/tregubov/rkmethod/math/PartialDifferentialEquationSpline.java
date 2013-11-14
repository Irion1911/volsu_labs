package com.tregubov.rkmethod.math;


public class PartialDifferentialEquationSpline {
	
	//Функция вида y" = F(x,y,y');
	private Function mF;
	private double x0 = 0,
			y0 = 0,
			y1 = 0;
	
	public PartialDifferentialEquationSpline(Function f){
		mF = f;
	}
	
	public PartialDifferentialEquationSpline(Function f, double x0, double y0, double y1){
		mF = f;
		
		this.x0 = x0;
		this.y0 = y0;
		this.y1 = y1;
	}
	
	public void setX0(double x0){
		this.x0 = x0;
	}
	
	public void setY0(double y0){
		this.y0 = y0;
	}
	
	public void setY1(double y1){
		this.y1 = y1;
	}
	
	public double lenght(double a, double b, double infelicity){
		
		//Вида y = F(x)
		Spline s = new Spline(new Function() {
			
			@Override
			public double calculate (double... args) {
				return 0;
			}
		});
		
		return s.length(a, b, infelicity);
	}
	
	private double o[][] = {
			{0,0,0,0},
			{0.5,0.5,0,0},
			{0.5,0,0.5,0},
			{1,0,0,1},
			{1/6,1/3,1/3,1/6}
			};
	
	//Функция вида y'=F(x,y)
	private double RungeKutta(Function f, double x0, double y0, double x1){
		double h = x1 - x0;
		double sK = 0;
		double oldK[] = {0, 0, 0, 0};
		
		for(int i=0;i<4;i++){
			 oldK[i] = f.calculate(x0 + h*o[i][0], y0 + o[i][1]*h*oldK[0] + o[i][2]*h*oldK[1] + o[i][3]*h*oldK[2]);
			 sK += oldK[i]*o[4][i];
		}
		
		return h*sK;
	}
	
}
