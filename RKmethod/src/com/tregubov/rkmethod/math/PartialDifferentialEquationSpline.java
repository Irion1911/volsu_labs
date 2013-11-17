package com.tregubov.rkmethod.math;


public class PartialDifferentialEquationSpline {
	
	private Function3 mF;
	private InitConditions mCond;
	private double o[][] = {
			{0,0,0,0},
			{0.5,0.5,0,0},
			{0.5,0,0.5,0},
			{1,0,0,1},
			{1/6,1/3,1/3,1/6}
			};
	private Function1 linFunction = new Function1() {
		private double h;

		@Override
		public double f (double x) {
			h = x - mCond.x0;
			
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
	
	private class InitConditions{
		private double x0, y0, y1;
	
		public InitConditions(double x0, double y0, double y1){
			this.x0 = x0;
			this.y0 = y0;
			this.y1 = y1;
		}
	}
	
	public PartialDifferentialEquationSpline(Function3 f, double x0, double y0, double y1){
		mF = f;
		mCond = new InitConditions(x0, y0, y1);
	}
	
	public Function1 getFunction(){
		return linFunction;
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
