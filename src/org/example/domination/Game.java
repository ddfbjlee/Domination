package org.example.domination;

import org.example.domination.model.Board;
import org.example.domination.model.Player;
import org.example.domination.model.Piece;
import org.example.domination.model.Position;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

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
		Player player1 = new Player(1);
		Player player2 = new Player(2);
		
		// Initialize pieces in set locations for testing.
		board.setPiece(new Piece(R.drawable.greenblob1, R.drawable.greenblob1_selected, 1, player1), 0, 0);
		board.setPiece(new Piece(R.drawable.redblob2, R.drawable.redblob2_selected, 2, player2), 0, 2);
		board.setPiece(new Piece(R.drawable.greenblob3, R.drawable.greenblob3_selected, 3, player1), 0, 4);
		
		boardView = new BoardView(this);
		setContentView(boardView, new ViewGroup.LayoutParams(720, 720));
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
	 *    a) If the selected piece belongs to the same player, select it.
	 *    b) The selected piece belongs to the opposing player.
	 *    	i)   If the current piece is stronger than the selected piece, remove the selected piece and move the current piece.
	 *    	ii)  If the current piece is weaker than the selected piece, remove the current piece.
	 *    	iii) If the current piece is the same strength as the selected piece, remove both.
	 * Currently always returns true, until we have a case where we'd want to stop
	 * handling subsequent events in the gesture.
	 *       
	 * @param x
	 * @param y
	 * @return true
	 */
	protected boolean selectTile(int x, int y) {
		Log.d(TAG, "Calling selectTile on x: " + x + " y: " + y);
		if (selectedPosition != null) {
			Log.d(TAG, "Selected position x: " + selectedPosition.getX() + selectedPosition.getY());
		}
		Position targetPosition = new Position(x, y);
		Piece targetPiece = board.getPiece(targetPosition);
		Piece currentPiece = board.getPiece(selectedPosition);
		if (targetPiece == null) {
			if (selectedPosition == null) {
				return true;
			} else {
				Log.d(TAG, "Moving piece");
				board.movePiece(selectedPosition, targetPosition);
				selectedPosition = null;
				return true;
			}
		} else {			
			if(currentPiece == null) {
				Log.d(TAG, "Setting selected position");
				selectedPosition = targetPosition;
				return true;
			} else {
				Player currentPiecePlayer = currentPiece.getPlayer();
				Player targetPiecePlayer = targetPiece.getPlayer();
				int currentPlayer = currentPiecePlayer.getPlayerID();
				int targetPlayer = targetPiecePlayer.getPlayerID();
				if(currentPlayer == targetPlayer) {
					Log.d(TAG, "Setting selected position");
					selectedPosition = targetPosition;
					return true;
				} else {
					if(currentPiece.getStrength() > targetPiece.getStrength())
					{
						Log.d(TAG, "Current Piece replaces selected Piece");
						board.movePieceAndDestroy(selectedPosition, targetPosition);
						return true;
					}
					else if(currentPiece.getStrength() < targetPiece.getStrength()) {
						Log.d(TAG, "Selected Piece gets destroyed");
						board.destroyPiece(selectedPosition);
						return true;
					}
					else {
						Log.d(TAG, "Both current and selected pieces get destroyed");
						board.destroyPiece(selectedPosition);
						board.destroyPiece(targetPosition);
						return true;
					}
				}
			}
		}
	}
}
