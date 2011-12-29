package org.example.domination.model;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object other) {
		boolean result = false;
	    if (other instanceof Position) {
	    	Position that = (Position) other;
	        result = (this.getX() == that.getX() && this.getY() == that.getY());
	    }
	    return result;
	}
	
	@Override
	public int hashCode() {
        return (41 * (41 + getX()) + getY());
    }
}
