package com.tregubov.rkmethod;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import com.tregubov.rkmethod.widgets.MathTextView;

public class MainActivity extends Activity {

	String f = "y'' + y'*x*e^y + arctg(x + y) + x^2 = 0";

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		MathTextView t = (MathTextView) findViewById(R.id.textview);
		t.setTextMath(f);
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
		
	}

}
