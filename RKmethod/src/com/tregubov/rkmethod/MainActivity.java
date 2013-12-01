package com.tregubov.rkmethod;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

import com.tregubov.rkmethod.math.Function3;
import com.tregubov.rkmethod.math.Target;

public class MainActivity extends Activity implements OnClickListener {

	//TODO завернуть в настройки
	private double mAccuracy = 0.000001;
	private int a = 0, b= 1;
	Target mTarget = new Target(new Function3() {

		@Override
		public double f (double x, double y, double yy) {
			return -x * x - Math.atan(x + yy) - yy * x * Math.exp(yy);
		}
	}, "y''+y'xe^y+arctg(x+y)+x^2 = 0", 0., Math.PI);

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		ImageButton settings = (ImageButton) findViewById(R.id.button_settings);
		settings.setOnClickListener(this);
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
			break;
		}
	}

}
