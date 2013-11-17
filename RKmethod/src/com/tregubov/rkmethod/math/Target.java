package com.tregubov.rkmethod.math;

public class Target {

	private Function3 mF;
	private String mName;
	double mX0, mY0;
	
	public Target(Function3 f, String name, double x0, double y0){
		mName = name;
		mF = f;
		mX0 = x0;
		mY0 = y0;
	}
	
	public Function3 getFunction(){
		return mF;
	}
	
	public String getName(){
		return mName;
	}
	
	public double getX(){
		return mX0;
	}
	
	public double getY(){
		return mY0;
	}
}
