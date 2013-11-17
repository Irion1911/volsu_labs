package com.tregubov.rkmethod.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Graph extends View {
	private Paint axisColor, upColor, bottomColor, targetColor;

	public Graph(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	public Graph(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public Graph(Context context) {
		super(context);
		init();
	}
	
	private void init(){
		axisColor = new Paint();
		axisColor.setColor(Color.WHITE);
		
		upColor = new Paint();
		upColor.setColor(Color.RED);
		
		targetColor = new Paint();
		targetColor.setColor(Color.GREEN);
		
		bottomColor = new Paint();
		bottomColor.setColor(Color.YELLOW);
	}
	
	@Override
	protected void onDraw (Canvas canvas) {
		super.onDraw(canvas);
		int maxX = canvas.getWidth();
		int maxY = canvas.getHeight();
		
		canvas.drawLine(maxX/2, 0, maxX/2, maxY, axisColor);
		canvas.drawLine(0, maxY/2, maxX, maxY/2, axisColor);
		canvas.drawText("0", maxX/2, maxY/2, axisColor);
	}

}
