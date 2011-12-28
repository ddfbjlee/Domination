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
import android.view.View;

public class BoardView extends View {
	private final Game game;
	private float width;	//width of one tile
	private float height;	//height of one tile
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
		for (Position position : positions) {
			Piece piece = board.getPiece(position);
			// This is somewhat inefficient if it's known that one player's pieces will always use a particular
			// image. If that's the case, we should decode the bitmap once at the start of the game and reuse it.
			Bitmap image = BitmapFactory.decodeResource(getResources(), piece.getResourceID());
			canvas.drawBitmap(image, width * position.getX(), height * position.getY(), null);
		}
	}
}
