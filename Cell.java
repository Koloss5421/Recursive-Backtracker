package com.jhardin;

public class Cell{
	
	int xLoc = 0;
	int yLoc = 0;
	int gridX, gridY;
	boolean[] walls = {true, true, true, true};
	boolean explored = false;
	
	public Cell(int x, int y, int gridSize, int cellWidth) {
		 
		gridX = x;
		gridY = y;
		
		xLoc = (x * cellWidth);
		yLoc = (y * cellWidth);
		
	}
	
	public boolean[] getWalls() {
		return walls;
	}
	public void setWalls(boolean newWalls, int i) {
		walls[i] = newWalls;	
	}
	public int getX() {
		return xLoc;
	}
	public  int getY(){
		return yLoc;
	}
	public boolean getExplored() {
		return explored;
	}
	public void setExplored(boolean explore) {
		explored = explore;
	}
	public int getGridX() {
		return gridX;
	}
	public int getGridY() {
		return gridY;
	}
	
}
