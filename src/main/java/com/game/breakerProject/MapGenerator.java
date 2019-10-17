package com.game.breakerProject;

public class MapGenerator {

	private int map[][];
	private int brickWidth;
	private int brickHeight;
	private static final int WIDTH = 600, HEIGHT = 400;
	
	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public int getBrickWidth() {
		return brickWidth;
	}

	public void setBrickWidth(int brickWidth) {
		this.brickWidth = brickWidth;
	}

	public int getBrickHeight() {
		return brickHeight;
	}

	public void setBrickHeight(int brickHeight) {
		this.brickHeight = brickHeight;
	}

	public MapGenerator(int row, int col) {
		map = new int[row][col];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				map[i][j] = 1;
			}
		}

		brickWidth = 540 / col;
		brickHeight = 150 / row;
	}

	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}

	public int getWidth() {
		return WIDTH;
	}

	public int getHeight() {
		return HEIGHT;
	}
}
