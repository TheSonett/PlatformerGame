package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import inputs.KeyInputs;
import inputs.MouseInputs;

import static animations.PlayerActions.*;
import static animations.PlayerActions.Directions.*;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private float xDelta = 100, yDelta = 100;
	private BufferedImage player;
	private BufferedImage[][] playerAnimations;
	private int animationTick, animationIndex, animationSpeed = 15;
	private int playerAction = PAUSE;
	private int playerDir = -1;
	private boolean moving = false;
	
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(1280, 800));
		
		importImage();
		loadAnimations();
		
		addKeyListener(new KeyInputs(this));
		addMouseListener(new MouseInputs(this));
		addMouseMotionListener(new MouseInputs(this));
	}
	
	private void importImage() {
		InputStream is = getClass().getResourceAsStream("/player.png");
		try {
			player = ImageIO.read(is);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close(); // free the resources
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void loadAnimations() {
		playerAnimations = new BufferedImage[9][6];
		
		for(int col = 0; col < playerAnimations.length; col++) {
			for(int row = 0; row < playerAnimations[col].length; row++) {
				playerAnimations[col][row] = player.getSubimage(row*64, col*40, 64, 40);
			}			
		}
	}
	
	
	public void setMovement(int direction) {
		this.playerDir = direction;
		moving = true;
		
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	private void updateAnimation() {
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= getPlayerAmount(playerAction)) {
				animationIndex = 0;
			}
		}
	}
	
	public void updateGame() {
		updateAnimation();
		setAnimation();
		updatePos();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.drawImage(playerAnimations[playerAction][animationIndex], (int)xDelta, (int)yDelta, 250, 160, null);
		// repaint done in game thread/loop
	}
	
	private void setAnimation() {
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = PAUSE;
		}
	}
	
	private void updatePos() {
		if(moving) {
			switch(playerDir) {
			case LEFT:
				xDelta -= 5;
				break;
			case RIGHT:
				xDelta += 5;
				break;
			case UP:
				yDelta -= 5;
				break;
			case DOWN:
				yDelta += 5;
				break;
			}
		}
	}
}
