package org.example.domination;

import org.example.domination.model.Board;
import org.example.domination.model.Piece;
import org.example.domination.model.Position;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class Game extends Activity {
	// Logging
	private static final String TAG = "domination.Game";
	
	private Board board;
	private BoardView boardView;
	
	// Gameplay
	private Position selectedPosition;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Initialize game components.
		board = new Board();
		selectedPosition = null;
		
		// Initialize pieces in set locations for testing.
		board.setPiece(new Piece(R.drawable.blob, R.drawable.blob_selected), 0, 0);
		board.setPiece(new Piece(R.drawable.blob, R.drawable.blob_selected), 0, 2);
		board.setPiece(new Piece(R.drawable.blob, R.drawable.blob_selected), 0, 4);
		
		boardView = new BoardView(this);
		setContentView(boardView);
		boardView.requestFocus();
	}
	
	/**
	 * Getter for the game board.
	 * @return the board
	 */
	protected Board getBoard() {
		return board;
	}
	
	/**
	 * Getter for the currently selected position.
	 * @return the selected position
	 */
	protected Position getSelectedPosition() {
		return selectedPosition;
	}
	
	/**
	 * Business logic for interacting with the game.
	 * 1) There is no piece in the selected tile.
	 *    a) If there is no piece selected, do nothing.
	 *    b) If there is a piece selected, move it there.
	 * 2) There is a piece in the selected tile.
	 *    a) Select it.
	 *       
	 * @param x
	 * @param y
	 * @return whether or not we did something (only false in 1a right now)
	 */
	protected boolean selectTile(int x, int y) {
		Log.d(TAG, "Calling selectTile on x: " + x + " y: " + y);
		if (selectedPosition != null) {
			Log.d(TAG, "Selected position x: " + selectedPosition.getX() + selectedPosition.getY());
		}
		Position targetPosition = new Position(x, y);
		Piece targetPiece = board.getPiece(targetPosition);
		if (targetPiece == null) {
			if (selectedPosition == null) {
				return false;
			} else {
				Log.d(TAG, "Moving piece");
				board.movePiece(selectedPosition, targetPosition);
				selectedPosition = null;
				return true;
			}
		} else {
			Log.d(TAG, "Setting selected position");
			selectedPosition = targetPosition;
			return true;
		}
	}
}
