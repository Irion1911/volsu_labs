package com.tregubov.rkmethod.math;

import java.util.List;


public class PartialDifferentialEquationSpline {
	
	private List<InitConditions> mConditions;
	private Function3 mF;
	private double mAccuracy;
	private double o[][] = {
			{0,0,0,0},
			{0.5,0.5,0,0},
			{0.5,0,0.5,0},
			{1,0,0,1},
			{1/6,1/3,1/3,1/6}
			};
	
	private Function1 resultF = new Function1() {
		private double h;

		@Override
		public double f (double x) {
			int n = (int) Math.ceil((x - mCond.x0)*Math.pow((x - mCond.x0)/mAccuracy, 0.25));
			h = (x - mCond.x0)/n;
			
			return RungeKutta(new Function2() {
				private double mY;

				@Override
				public double f (double x, double y) {
					mY = y;
					
					return RungeKutta(new Function2() {
						
						@Override
						public double f (double x, double y) {
							return mF.f(x, mY, y);
						}
					}, mCond.x0, mCond.y1, h);
				}

			}, mCond.x0, mCond.y0, h);
		}
	};
	private Function2 differencialF = new Function2() {
		private double mY;

		@Override
		public double f (double x, double y) {
			mY = y;
			double h = x - oldx;
			
			return RungeKutta(new Function2() {
				
				@Override
				public double f (double x, double y) {
					return mF.f(x, mY, y);
				}
			}, mCond.x0, mCond.y1, h);
		}

	};
	
	private class InitConditions{
		private double x0, y0, y1;
	
		public InitConditions(double x0, double y0, double y1){
			this.x0 = x0;
			this.y0 = y0;
			this.y1 = y1;
		}
	}
	
	public PartialDifferentialEquationSpline(Function3 f, double x0, double y0, double y1, double accuracy){
		mF = f;
		mConditions.add(new InitConditions(x0, y0, y1));
		mAccuracy = accuracy;
	}
	
	public double getDifferencial(double x){
		return differencialF.f(x, resultF.f(x));
	}
	
	public Function1 getFunction(){
		return resultF;
	}
	
	private double RungeKutta(Function2 f, double x0, double y0, double h){
		double sK = 0;
		double oldK[] = {0, 0, 0, 0};
		
		for(int i=0;i<4;i++){
			 oldK[i] = f.f(x0 + h*o[i][0], y0 + o[i][1]*h*oldK[0] + o[i][2]*h*oldK[1] + o[i][3]*h*oldK[2]);
			 sK += oldK[i]*o[4][i];
		}
		
		return y0 + h*sK;
	}
	
}
