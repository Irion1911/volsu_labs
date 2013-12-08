package com.tregubov.rkmethod.widgets;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tregubov.rkmethod.R;
import com.tregubov.rkmethod.math.Function1;

public class Graph extends SurfaceView implements SurfaceHolder.Callback {
	private Paint axisColor;
	private final int defColor = 0;
	private DrawThread drawThread;
	
	private double xMax, yMax, xMin, yMin;
	private int xStep, yStep;
	private Point zero;
	
	
	List<Function1> mFunctions = new ArrayList<Function1>();

	public Graph(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(attrs);
	}

	public Graph(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(attrs);
	}

	public Graph(Context context) {
		super(context);
		init(null);
	}

	public void addFunction (Function1 function) {
		mFunctions.add(function);
	}

	private void init (AttributeSet attrs) {
		int color = defColor;

		if (attrs != null) {
			TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.Graph);
			color = a.getDimensionPixelSize(R.styleable.Graph_axis_color, defColor);
			a.recycle();
		}
		
		xMax = yMax = xMin = yMin = 1;

		axisColor = new Paint();
		axisColor.setColor(color);

		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated (SurfaceHolder holder) {
		drawThread = new DrawThread(getHolder(), getResources());
		drawThread.start();
	}

	@Override
	public void surfaceDestroyed (SurfaceHolder holder) {
		drawThread.end();
	}

}

class DrawThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private boolean isRunning;

	public DrawThread(SurfaceHolder surfaceHolder, Resources resources) {
		this.surfaceHolder = surfaceHolder;
	}
	
	@Override
	public synchronized void start () {
		isRunning = true;
		super.start();
	}
	
	public void end(){
		isRunning = false;
		try {
			this.join();
		} catch (InterruptedException e) {
		}
	}
	
	@Override
	public void run () {
		Canvas canvas;
		if(isRunning){
//			canvas = surfaceHolder.lockCanvas(null);
//			
//			xStep = (int)(canvas.getWidth()/(xMax + xMin));
//			yStep = (int)(canvas.getHeight()/(yMax = yMin));
//			
//			zero = new Point((int)(xStep * xMin), (int)(yStep * yMax));
//			canvas.drawLine(0, zero.y, canvas.getWidth(), zero.y, axisColor);
//			canvas.drawLine(zero.x, 0, zero.x, canvas.getHeight(), axisColor);
//			getHolder().unlockCanvasAndPost(canvas);
		}
		
		while (isRunning) {

			canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					// TODO рисовальная часть
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}
