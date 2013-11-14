package com.tregubov.rkmethod;

import java.util.ArrayList;
import java.util.List;

import com.tregubov.rkmethod.math.Function;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class MainActivity extends Activity {
	
	private List<Function> mFunctions = new ArrayList<Function>();

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		init();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}
	
	private void init(){
		mFunctions.add(new Function() {
			
			@Override
			public double calculate (double... args) {
				double x = args[0],
						y0 = args[1],
						y1 = args[2];
				return -x*x -Math.atan(x+y0) -y1*x*Math.exp(y0);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;

	}

}
