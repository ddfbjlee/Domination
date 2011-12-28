package org.example.domination;

import org.example.domination.model.Board;
import org.example.domination.model.Piece;

import android.app.Activity;
import android.os.Bundle;

public class Game extends Activity {
	private Board board;
	private BoardView boardView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// Initialize Board.
		board = new Board();
		
		// Initialize pieces in set locations for testing.
		board.setPiece(new Piece(R.drawable.blob), 0, 0);
		board.setPiece(new Piece(R.drawable.blob), 0, 2);
		board.setPiece(new Piece(R.drawable.blob), 0, 4);
		
		boardView = new BoardView(this);
		setContentView(boardView);
		boardView.requestFocus();
	}
	
	protected Board getBoard() {
		return board;
	}
}
