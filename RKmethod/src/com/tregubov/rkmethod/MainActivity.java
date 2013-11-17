package com.tregubov.rkmethod;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		ImageButton settings = (ImageButton) findViewById(R.id.button_settings);
		settings.setOnClickListener(this);

		// mText = (TextView) findViewById(R.id.text);
		// ListView list = (ListView) findViewById(R.id.list);

		// TargetAdapter adapter = new TargetAdapter(this);
		// list.setAdapter(adapter);
		// list.setOnItemClickListener(this);

		// List<Target> objects = new ArrayList<Target>();
		// init(objects);
		// adapter.setObjects(objects);

	}

	// private void init(List<Target> list){
	// list.add(new Target(new Function3() {
	//
	// @Override
	// public double f (double x, double y, double yy) {
	// return -x*x -Math.atan(x+yy) -yy*x*Math.exp(yy);
	// }
	// }, "y''+y'xe^y+arctg(x+y)+x^2 = 0", 0, Math.PI));
	//
	// }

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick (View v) {
		switch(v.getId()){
			case R.id.button_function:
				break;
			
			case R.id.button_settings:
				break;
		}
	}

	// mText.setText(String.valueOf(new
	// PartialDifferentialEquationSpline(target.getFunction(), target.getX(),
	// target.getY(), 0).lenght(0, 1, 0.000001)));

}
