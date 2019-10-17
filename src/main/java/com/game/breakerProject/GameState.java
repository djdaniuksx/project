package com.game.breakerProject;

public class GameState {	
	
	private int map[][];

	private int totalBricks = 21;
	private int score = 0;

	private int playerX = 310;

	private int ballposX = 120;
	private int ballposY = 350;

	private int ballXdir = -1;
	private int ballYdir = -2;
	
	public GameState(int resW, int resH) {
		ballposY = resW / 2;
		ballposX = resH / 2;
	}

	public int[][] getMap() {
		return map;
	}

	public void setMap(int[][] map) {
		this.map = map;
	}

	public int getTotalBricks() {
		return totalBricks;
	}

	public void setTotalBricks(int totalBricks) {
		this.totalBricks = totalBricks;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getPlayerX() {
		return playerX;
	}

	public void setPlayerX(int playerX) {
		this.playerX = playerX;
	}

	public int getBallposX() {
		return ballposX;
	}

	public void setBallposX(int ballposX) {
		this.ballposX = ballposX;
	}

	public int getBallposY() {
		return ballposY;
	}

	public void setBallposY(int ballposY) {
		this.ballposY = ballposY;
	}

	public int getBallXdir() {
		return ballXdir;
	}

	public void setBallXdir(int ballXdir) {
		this.ballXdir = ballXdir;
	}

	public int getBallYdir() {
		return ballYdir;
	}

	public void setBallYdir(int ballYdir) {
		this.ballYdir = ballYdir;
	}	
}


