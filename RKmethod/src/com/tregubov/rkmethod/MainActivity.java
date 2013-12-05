package com.tregubov.rkmethod;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

import com.tregubov.rkmethod.math.Function1;
import com.tregubov.rkmethod.math.Function3;
import com.tregubov.rkmethod.math.Optimization;
import com.tregubov.rkmethod.math.PartialDifferentialSpline;
import com.tregubov.rkmethod.math.Target;

public class MainActivity extends Activity implements OnClickListener {

	// TODO завернуть в настройки
	private double mAccuracy = 0.000001;
	private int a = 0, b = 1;
	Target mTarget = new Target(new Function3() {

		@Override
		public double f (double x, double y, double yy) {
			return -x * x - Math.atan(x + yy) - yy * x * Math.exp(yy);
		}
	}, "y''+y'xe^y+arctg(x+y)+x^2 = 0", 0., Math.PI);

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick (View v) {
		switch (v.getId()) {
		case R.id.button_function:
			break;
		case R.id.button_settings:
//			break;
//		case R.id.button_play:
			PartialDifferentialSpline spline = new PartialDifferentialSpline(mTarget.getFunction(), mTarget.getX(), mTarget.getY(), Optimization.GoldenSection(new Function1() {
				
				@Override
				public double f (double x) {
					return new PartialDifferentialSpline(mTarget.getFunction(), mTarget.getX(), mTarget.getY(), x, mAccuracy).getFunction().length(a, b, mAccuracy);
				}
			}, a, b, mAccuracy), mAccuracy);
			break;
//		case R.id.button_stop:
//			break;

		}
	}
}
