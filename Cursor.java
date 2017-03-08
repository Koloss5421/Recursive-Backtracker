package com.jhardin;

import java.util.Stack;
import java.util.Random;

public class Cursor {
	
	Cell currentCell;
	Cell nextCell;
	Stack<Cell> stack = new Stack<Cell>();
	
	public Cursor(Cell current) {
		//Random r = new Random();
		currentCell = current;
		
		if (currentCell != null) {
			currentCell.setExplored(true);
			stack.push(currentCell);
		}
		
	}
	public void updateCursor(Cell[][] grid) {
		
		currentCell.setExplored(true);
		Random r = new Random();
		Cell top = null, right = null, bot = null, left = null;
		
		boolean hasNeighbor = true;
		int numNeighbors = 0;
		
		boolean[] neighbors = {false, false, false, false};
		
		if (currentCell.getGridY() - 1 != -1) {
			top = grid[currentCell.getGridX()][currentCell.getGridY() - 1];
			if (!top.getExplored()) {
				neighbors[0] = true;
				
			}
		}
		if (currentCell.getGridX() + 1 < grid.length) {
			right = grid[currentCell.getGridX() + 1][currentCell.getGridY()];
			if (!right.getExplored()) {
				neighbors[1] = true;
			}
		}
		if (currentCell.getGridY() + 1 < grid.length) {
			bot = grid[currentCell.getGridX()][currentCell.getGridY() + 1];
			if (!bot.getExplored()) {
				neighbors[2] = true;
			}
		}
		if (currentCell.getGridX() - 1 != -1) {
			left = grid[currentCell.getGridX() - 1][currentCell.getGridY()];
			if (!left.getExplored()) {
				neighbors[3] = true;
			}
		}
		
		for (int k = 0; k < neighbors.length; k++) {
			if(neighbors[k]) {
				numNeighbors++;
			}
		}
		
		if (numNeighbors > 0) {
			hasNeighbor = false;
		}
		else
		{
			if (!stack.isEmpty()) {
				nextCell = stack.pop();
			}
		}
		
		while (!hasNeighbor) {		
			int j = r.nextInt(4);

			if (neighbors[j]) {
				switch (j) {
				case 0:
					currentCell.setWalls(false, j);
					nextCell = top;
					nextCell.setWalls(false, 2);
					hasNeighbor = true;
					break;
				case 1:
					currentCell.setWalls(false, j);
					nextCell = right;
					nextCell.setWalls(false, 3);
					hasNeighbor = true;
					break;
				case 2:
					currentCell.setWalls(false, j);
					nextCell = bot;
					nextCell.setWalls(false, 0);
					hasNeighbor = true;
					break;
				case 3:
					currentCell.setWalls(false, j);
					nextCell = left;
					nextCell.setWalls(false, 1);
					hasNeighbor = true;
					break;
				}
			}
		}
		if (nextCell == top || nextCell == right || nextCell == bot || nextCell == left) {
			currentCell = nextCell;
			stack.push(currentCell);
			nextCell = null;
		}
		else
		{
			currentCell = nextCell;
			nextCell = null;
		}
		if (stack.size() < 1) {
			Main main = new Main();
			main.pause();
		}
	}
	
	public Cell getCurrent() {
		return currentCell;
	}

}
