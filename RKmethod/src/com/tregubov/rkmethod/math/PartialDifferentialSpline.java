package com.tregubov.rkmethod.math;

import java.util.List;

public class PartialDifferentialSpline {

	private List<InitConditions> mConditions;
	private Function3 mF;
	private double mAccuracy;
	
	private final int gradeH = 5;
	private final double o[][] = { { 0, 0, 0, 0 }, { 0.5, 0.5, 0, 0 }, { 0.5, 0, 0.5, 0 }, { 1, 0, 0, 1 }, { 1 / 6, 1 / 3, 1 / 3, 1 / 6 } };

	private InitConditions getCondition (double x) {
		InitConditions tmp = findNearestCondition(mConditions, x);
		if(tmp.x0 == x)
			return tmp;
		
		double canonicalStep = Math.pow(mAccuracy/Math.abs(x - tmp.x0), 1/(gradeH - 1));
		final double h;
		if(Math.abs(x - tmp.x0) > canonicalStep){
			h = canonicalStep;
			tmp = getCondition(x + ((x < tmp.x0)? canonicalStep : -canonicalStep));
		} else
			h = Math.abs(x - tmp.x0);
		
		final InitConditions t = tmp;

		double y = RungeKutta(new Function2() {
			private double mY;

			@Override
			public double f (double x, double y) {
				mY = y;

				return RungeKutta(new Function2() {

					@Override
					public double f (double x, double y) {
						return mF.f(x, mY, y);
					}
				}, t.x0, t.y1, h);
			}

		}, t.x0, t.y0, h);

		InitConditions res = new InitConditions(x, y, getDifferencial(h, y, t));
		mConditions.add(mConditions.indexOf(t) + ((t.x0 < x) ? 1 : 0), res);
		return res;
	}

	private double getDifferencial (double h, double y, InitConditions cond) {
		final double mY = y;

		return RungeKutta(new Function2() {

			@Override
			public double f (double x, double y) {
				return mF.f(x, mY, y);
			}
		}, cond.x0, cond.y1, h);
	}

	private InitConditions findNearestCondition (List<InitConditions> conditions, double x) {
		if (conditions.size() == 1)
			return conditions.get(0);
		else {
			int j = conditions.size() - 1;
			for (int i = 1; i < conditions.size(); i++) {
				if (Math.abs(conditions.get(i).x0 - x) > Math.abs(conditions.get(i - 1).x0 - x)) {
					j = i - 1;
					break;
				}
			}
			return conditions.get(j - 1);
		}
	}

	private class InitConditions {
		private double x0, y0, y1;

		public InitConditions(double x0, double y0, double y1) {
			this.x0 = x0;
			this.y0 = y0;
			this.y1 = y1;
		}
	}

	public PartialDifferentialSpline(Function3 f, double x0, double y0, double y1, double accuracy) {
		mF = f;
		mConditions.add(new InitConditions(x0, y0, y1));
		mAccuracy = accuracy;
	}
	
	public Function1 getFunction(){
		return new Function1() {
			
			@Override
			public double f (double x) {
				return getCondition(x).y0;
			}
		};
	}
	
	public Function1 getDifferentialFunction(){
		return new Function1() {
			
			@Override
			public double f (double x) {
				return getCondition(x).y1;
			}
		};
	}

	private double RungeKutta (Function2 f, double x0, double y0, double h) {
		double sK = 0;
		double oldK[] = { 0, 0, 0, 0 };

		for (int i = 0; i < 4; i++) {
			oldK[i] = f.f(x0 + h * o[i][0], y0 + o[i][1] * h * oldK[0] + o[i][2] * h * oldK[1] + o[i][3] * h * oldK[2]);
			sK += oldK[i] * o[4][i];
		}

		return y0 + h * sK;
	}

}
