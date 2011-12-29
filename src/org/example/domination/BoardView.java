package org.example.domination;

import java.util.Set;

import org.example.domination.model.Board;
import org.example.domination.model.Piece;
import org.example.domination.model.Position;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class BoardView extends View {
	// Logging
	private static final String TAG = "domination.BoardView";
	
	// Context
	private final Game game;
	
	// Board configuration
	private float width;	//width of one tile
	private float height;	//height of one tile
	
	// ?
	private int selX;		//X index of selection
	private int selY;		//Y index of selection
	private final Rect selRect = new Rect();
	
	public BoardView (Context context) {
		super(context);
		this.game = (Game) context;
		setFocusable(true);
		setFocusableInTouchMode(true);
	}

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
		Log.d(TAG, "Drawing the board");
		Paint background = new Paint();
		background.setColor(getResources().getColor(R.color.board_background));
		Paint dark = new Paint();
		dark.setColor(getResources().getColor(R.color.square_dark));
		
		for (int i = 0; i < 10; i++) {
			canvas.drawLine(0, i * height, getWidth(), i * height, dark);
			canvas.drawLine(i * width, 0, i * width, getHeight(), dark);
		}
		
		// Draw all the pieces on the board.
		Board board = game.getBoard();
		Set<Position> positions = board.getPositions();
		Position sp = game.getSelectedPosition();
		if (sp != null) {
			Log.d(TAG, "Selected position x: " + sp.getX() + " y: " + sp.getY());
		} else {
			Log.d(TAG, "No selected position");
		}
		for (Position position : positions) {
			Piece piece = board.getPiece(position);
			int resourceID;
			if (position.equals(game.getSelectedPosition())) {
				Log.d(TAG, "Drawing piece at position x: " + position.getX() + " y: " + position.getY() + " with selected state");
				resourceID = piece.getSelectedResourceID();
			} else {
				Log.d(TAG, "Drawing piece at position x: " + position.getX() + " y: " + position.getY() + " with normal state");
				resourceID = piece.getResourceID();
			}
			
			// This is somewhat inefficient if it's known that one player's pieces will always use a particular
			// image. If that's the case, we should decode the bitmap once at the start of the game and reuse it.
			Bitmap image = BitmapFactory.decodeResource(getResources(), resourceID);
			canvas.drawBitmap(image, width * position.getX(), height * position.getY(), null);
		}
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
	    float x = event.getX();
	    float y = event.getY();
	    Log.d(TAG, "Touch event triggered at x: " + x + " y: " + y);
	    
	    // Normalize the touch coordinates to a board position.
	    // Currently assuming fixed 10x10 board like the rest of this class.
	    int normX = (int) (x / width);
	    int normY = (int) (y / height);
	    
	    if (action == MotionEvent.ACTION_DOWN) {
	    	// Not sure how critical the return value actually is for this method.
	    	boolean result = game.selectTile(normX, normY);
	    	invalidate();
	    	return result;
	    } else {
	    	return false;
	    }
	}
}
