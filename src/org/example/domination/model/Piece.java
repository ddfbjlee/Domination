package org.example.domination.model;

public class Piece {
	private int resourceID;
	private int selectedResourceID;
	private int strength;
	private Player player;
	
	public Piece(int resourceID, int selectedResourceID, int strength, Player player) {
		this.resourceID = resourceID;
		this.selectedResourceID = selectedResourceID;
		this.strength = strength;
		this.player = player;
	}
	
	public int getResourceID() {
		return resourceID;
	}
	
	public int getSelectedResourceID() {
		return selectedResourceID;
	}
	
	public int getStrength() {
		return strength;
	}
	
	public Player getPlayer() {
		return player;
	}
	
}
