package org.example.domination.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Board {
	
	private Map<Position, Piece> pieces;
	
	/**
	 * Default constructor, initializes empty pieces map.
	 */
	public Board() {
		pieces = new HashMap<Position, Piece>();
	}
	
	public Set<Position> getPositions() {
		return pieces.keySet();
	}
	
	/**
	 * Gets the piece located at the given x-y coordinates.
	 * @param x
	 * @param y
	 * @return Piece, or null if no piece at those coordinates.
	 */
	public Piece getPiece(int x, int y) {
		return getPiece(new Position(x, y));
	}
	
	public Piece getPiece(Position pos) {
		return pieces.get(pos);
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
	
	/**
	 * If a piece exists at the starting position, move it to the
	 * ending position.
	 * @param startPos
	 * @param endPos
	 */
	public void movePiece(Position startPos, Position endPos) {
		Piece piece = pieces.get(startPos);
		if (piece != null) {
			pieces.remove(startPos);
			pieces.put(endPos, piece);
		}
	}
	
	public void movePieceAndDestroy(Position startPos, Position endPos) {
		Piece piece = pieces.get(startPos);
		if (piece != null) {
			pieces.remove(endPos);
			pieces.remove(startPos);
			pieces.put(endPos, piece);
		}
	}
	
	public void destroyPiece(Position pos)
	{
		pieces.remove(pos);
	}
}