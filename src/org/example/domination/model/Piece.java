package org.example.domination.model;

public class Piece {
	private int resourceID;
	private int selectedResourceID;
	
	public Piece(int resourceID, int selectedResourceID) {
		this.resourceID = resourceID;
		this.selectedResourceID = selectedResourceID;
	}
	
	public int getResourceID() {
		return resourceID;
	}
	
	public int getSelectedResourceID() {
		return selectedResourceID;
	}
}
