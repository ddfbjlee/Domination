package org.example.domination.model;

import java.util.HashMap;
import java.util.Map;

public class Board {
	
	public class Position {
		private int x;
		private int y;
		
		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		public int x() {
			return x;
		}
		
		public int y() {
			return y;
		}
	}
	
	private Map<Position, Piece> pieces;
	
	/**
	 * Default constructor, initializes empty pieces map.
	 */
	public Board() {
		pieces = new HashMap<Position, Piece>();
	}
	
	/**
	 * Gets the piece located at the given x-y coordinates.
	 * @param x
	 * @param y
	 * @return Piece, or null if no piece at those coordinates.
	 */
	public Piece getPiece(int x, int y) {
		return pieces.get(new Position(x, y));
	}
	
	/**
	 * Places the piece at the given coordinates, replacing
	 * any piece that might already exist at those coordinates.
	 * @param piece
	 * @param x
	 * @param y
	 */
	public void setPiece(Piece piece, int x, int y) {
		pieces.put(new Position(x, y), piece);
	}
}