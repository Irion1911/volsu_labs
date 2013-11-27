package com.tregubov.rkmethod.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.tregubov.rkmethod.R;

public class Graph extends SurfaceView implements SurfaceHolder.Callback {
	private Paint axisColor;
	private final int defColor = 0;
	DrawThread drawThread;

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
	private boolean runFlag = false;
	private SurfaceHolder surfaceHolder;
	private Matrix matrix;
	private long prevTime;

	public DrawThread(SurfaceHolder surfaceHolder, Resources resources) {
		this.surfaceHolder = surfaceHolder;

		matrix = new Matrix();
		matrix.postScale(3.0f, 3.0f);
		matrix.postTranslate(100.0f, 100.0f);

		prevTime = System.currentTimeMillis();
	}

	public void setRunning (boolean run) {
		runFlag = run;
	}

	@Override
	public void run () {
		Canvas canvas;
		while (runFlag) {
			long now = System.currentTimeMillis();
			long elapsedTime = now - prevTime;
			if (elapsedTime > 30) {
				prevTime = now;
			}
			canvas = null;
			try {
				canvas = surfaceHolder.lockCanvas(null);
				synchronized (surfaceHolder) {
					canvas.drawColor(Color.BLACK);
				}
			} finally {
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}
		}
	}
}
