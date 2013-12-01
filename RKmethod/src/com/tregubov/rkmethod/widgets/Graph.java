package com.tregubov.rkmethod.widgets;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tregubov.rkmethod.R;
import com.tregubov.rkmethod.math.Function1;

public class Graph extends SurfaceView implements SurfaceHolder.Callback {
	private Paint axisColor;
	private final int defColor = 0;
	DrawThread drawThread;
	
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

		axisColor = new Paint();
		axisColor.setColor(color);

		getHolder().addCallback(this);
	}

	@Override
	public void surfaceChanged (SurfaceHolder holder, int format, int width, int height) {}

	@Override
	public void surfaceCreated (SurfaceHolder holder) {
		drawThread = new DrawThread(getHolder(), getResources());
		drawThread.setRunning(true);
		drawThread.start();
	}

	@Override
	public void surfaceDestroyed (SurfaceHolder holder) {
		boolean retry = true;

		drawThread.setRunning(false);
		while (retry) {
			try {
				drawThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

}

class DrawThread extends Thread {
	private SurfaceHolder surfaceHolder;
	private long prevTime;
	private boolean isRunning;

	public DrawThread(SurfaceHolder surfaceHolder, Resources resources) {
		this.surfaceHolder = surfaceHolder;
		prevTime = System.currentTimeMillis();
	}
	
	public void setRunning(boolean run){
		isRunning = run;
	}

	@Override
	public void run () {
		Canvas canvas;
		while (isRunning) {
			long elapsedTime = System.currentTimeMillis() - prevTime;

			canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {//TODO рисовальная часть
					canvas.drawColor((int) -elapsedTime);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}
