package com.jhardin;

import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Main extends JPanel{
	/**
	 * Set Main Vars of Maze and window
	 */
	static String myTitle = "Maze Generator (Koloss)";
	static int windowWidth = 500;
	static int windowHeight = 500;
	static int gridSize = 10;
	static Cell[][] grid = new Cell[gridSize][gridSize];
	static int cellWidth = windowWidth / gridSize;
	static Cursor cursor;
	static boolean running;
	
	public static void main(String[] args) throws InterruptedException {
		
		final int FRAMES_PER_SEC = 25; //Cap Update Frames per second
		final int SKIP_TICKS = 1000 / FRAMES_PER_SEC;
		long next_game_tick = System.currentTimeMillis();
		int sleep_time = 0;
		running = true;		
		
		for (int x = 0; x < gridSize; x++) {
			for ( int y = 0; y < gridSize; y++) {
				Cell cell = new Cell(x, y, gridSize, cellWidth);
				grid[x][y] = cell;
				System.out.println("New Cell @ X: " + x + " Y: " + y);
			}
		}
		
		//CREATE CURSOR
		cursor = new Cursor(grid[0][0]);
		
		JFrame frame = new JFrame(myTitle);

		Main main = new Main();
		
		frame.setSize(windowWidth + 15, windowHeight + 40);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setMaximizedBounds(frame.getBounds());
		frame.setVisible(true);
		frame.add(main);
		
		
		//GAME LOOP
		
		while ( running ) {
			frame.repaint();
			cursor.updateCursor(grid);
			
			next_game_tick += SKIP_TICKS;
			sleep_time = (int) (next_game_tick - System.currentTimeMillis());
			
			if ( sleep_time >= 0 ) {
				Thread.sleep(sleep_time);
			}
		}
		

	}
	public void pause() {
		running = false;
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		setBackground(Color.DARK_GRAY);
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				g.setColor(Color.WHITE);
				
				Cell cell = grid[i][j];
				boolean[] walls = cell.getWalls();
	
				int cellX = cell.getX();
				int cellY = cell.getY();
				
				if (walls[0]) {
					g2.drawLine(cellX, cellY, cellX + cellWidth, cellY);
				}
				if (walls[1]) {
					g2.drawLine(cellX + cellWidth, cellY, cellX + cellWidth, cellY + cellWidth);
				}
				if (walls[2]) {
					g2.drawLine(cellX + cellWidth, cellY + cellWidth, cellX, cellY + cellWidth);
				}
				if (walls[3]) {
					g2.drawLine(cellX, cellY + cellWidth, cellX, cellY);
				}
				if (cell.getExplored()) {
					BasicStroke stroke = new BasicStroke(0);
					g2.setStroke(stroke);
					g2.setPaint(new Color(148, 0, 210));
					g2.fillRect(cellX + 1, cellY + 1, cellWidth, cellWidth);
				}
				Cell currentCell = cursor.getCurrent();
				BasicStroke stroke = new BasicStroke(0);
				g2.setStroke(stroke);
				g2.setPaint(Color.LIGHT_GRAY);
				g2.fillRect(currentCell.getX() + 1, currentCell.getY() + 1, cellWidth, cellWidth);
				
			}
			
		}
		
	}

}
