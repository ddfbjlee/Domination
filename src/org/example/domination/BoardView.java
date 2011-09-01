package org.example.domination;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;

public class BoardView extends View {
	private final Game game;
	public BoardView (Context context) {
		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}
	
	private float width;	//width of one tile
	private float height;	//height of one tile
	private int selX;		//X index of selection
	private int selY;		//Y index of selection
	private final Rect selRect = new Rect();
	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		width = w / 10f;
		height = h / 10f;
		getRect(selX, selY, selRect);
		super.onSizeChanged(w, h, oldw, oldh);
		
	}
	
	private void getRect(int x, int y, Rect rect) {
		rect.set((int) (x * height), (int) (y * height), (int) (x * width + width), (int) (y * height + height));
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.board_background));
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.square_dark));
		
		for (int i = 0; i < 10; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, dark);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
		}
	}
}
