package com.game.breakerProject;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class GamePlay extends JPanel implements KeyListener, ActionListener {

	private boolean play = false;

	private Timer timer;
	private int delay = 4; // ball speed

	private MapGenerator mapGen;
	private GameState state;

	public GamePlay(GameState state) {
		this.state = state;
		mapGen = new MapGenerator(3, 7);
		mapGen = mapGen.getMap();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}
	
	@Override
	public void paint(Graphics g) {
		// background
		g.setColor(Color.black);
		g.fillRect(1, 1, mapGen.getWidth(), mapGen.getHeight());

		// draw map
		draw((Graphics2D) g);

		// borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, mapGen.getHeight());
		g.fillRect(0, 0, mapGen.getWidth(), 3);
		g.fillRect(mapGen.getWidth(), 0, 3, mapGen.getHeight());

		// score
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD, 25));
		g.drawString("Score: " + state.getScore(), mapGen.getWidth(), 30);

		// the paddle
		g.setColor(Color.green);
		g.fillRect(state.getPlayerX(), mapGen.getWidth(), 100, 8);

		// ball
		g.setColor(Color.red);
		g.fillOval(state.getBallposX(), state.getBallposY(), 20, 20);

		if (state.getTotalBricks() <= 0) {
			play = false;
			state.setBallXdir(0);
			state.setBallYdir(0);
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("You Won!", mapGen.getWidth() / 2, mapGen.getHeight());
			g.drawString("Your Score is: " + state.getScore(), mapGen.getWidth() / 2, mapGen.getHeight() / 2);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press ENTER to Restart", mapGen.getHeight() / 2, mapGen.getHeight() - 100);
		}

		if (state.getBallposY() > mapGen.getWidth() / 2) {
			play = false;
			state.setBallXdir(0);
			state.setBallYdir(0);;
			g.setColor(Color.white);
			g.setFont(new Font("serif", Font.BOLD, 30));
			g.drawString("Game Over", mapGen.getWidth() / 2, mapGen.getHeight() / 2);
			g.drawString("Your Score is: " + state.getScore(), mapGen.getHeight() / 2, mapGen.getWidth() / 2);

			g.setFont(new Font("serif", Font.BOLD, 20));
			g.drawString("Press ENTER to Restart", mapGen.getWidth() / 2, mapGen.getHeight() - 100);
		}

		g.dispose();
	}

	public void draw(Graphics2D g) {
		for (int i = 0; i < mapGen.getMap().length; i++) {
			for (int j = 0; j < mapGen.getMap()[0].length; j++) {
				if (mapGen.getMap()[i][j] > 0) {
					g.setColor(Color.red);
					g.fillRect(j * mapGen.getBrickWidth() + 80, i * mapGen.getBrickHeight() + 50, mapGen.getBrickWidth(), mapGen.getBrickHeight());

					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j * mapGen.getBrickWidth() + 80, i * mapGen.getBrickHeight() + 50, mapGen.getBrickWidth(), mapGen.getBrickHeight());
				}
			}
		}
	}

	public void actionPerformed(ActionEvent e) {

		timer.restart();
		if (play) {
			
			if (new Rectangle(state.getBallposX(), state.getBallposY(), 20, 20).intersects(new Rectangle(state.getPlayerX(), 550, 100, 8))) {
				state.setBallYdir(-state.getBallYdir());
			}

			A: for (int i = 0; i < mapGen.getMap().length; i++) {
				for (int j = 0; j < mapGen.getMap()[0].length; j++) {
					if (mapGen.getMap()[i][j] > 0) {
						int brickX = j * mapGen.getBrickWidth() + 80;
						int brickY = i * mapGen.getBrickHeight() + 50;
						int brickWidth = mapGen.getBrickWidth();
						int brickHeight = mapGen.getBrickHeight();

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(state.getBallposX(), state.getBallposY(), 20, 20);
						Rectangle brickRect = rect;

						if (ballRect.intersects(brickRect)) {
							mapGen.setBrickValue(0, i, j);
							state.setTotalBricks(state.getTotalBricks() - 1);
							state.setScore(state.getScore() + 5);

							if (state.getBallposX() + 19 <= brickRect.x || state.getBallposX() + 1 >= brickRect.x + brickRect.width) {
								state.setBallXdir(-state.getBallXdir());
							} else {
								state.setBallYdir(-state.getBallYdir());
							}

							break A;
						}
					}
				}
			}

			state.setBallposX(+state.getBallXdir());
			state.setBallposY(+state.getBallYdir());

			if (state.getBallposX() < 0) {
				state.setBallXdir(-state.getBallXdir());
			}
			if (state.getBallposY() < 0) {
				state.setBallYdir(-state.getBallYdir());
			}
			if (state.getBallposX() > 670) {
				state.setBallXdir(-state.getBallXdir());
			}
		}
		repaint();
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (state.getPlayerX() >= mapGen.getWidth()) {
				state.setPlayerX(mapGen.getWidth());
			} else {
				moveRight();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (state.getPlayerX() < 10) {
				state.setPlayerX(10);
			} else {
				moveLeft();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			if (!play) {
				play = true;
				state.getBallposX();
				state.getBallposY();
				state.getBallXdir();
				state.getBallYdir();
				state.getPlayerX();
				state.getScore();
				state.getTotalBricks();
				mapGen = new MapGenerator(3, 7);

				repaint();
			}
		}

	}

	public void moveRight() {
		play = true;
		state.setPlayerX(+20);
	}

	public void moveLeft() {
		play = true;
		state.setPlayerX(-20);
	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}

}
