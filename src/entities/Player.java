package entities;

import static utils.Constants.PlayerConstants.*;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import utils.LoadSave;

public class Player extends Entity{
	private BufferedImage[][] playerAnimations;
	private int animationTick, animationIndex, animationSpeed = 15;
	private int playerAction = PAUSE;
	
	private boolean moving = false;
	private boolean attacking = false;
	
	private boolean left, right, up, down;
	private float playerSpeed = 2.0f;
	
	
	public Player(int x, int y) {
		super(x, y);
		loadAnimations();
	}
	
	
	public void update() {
		updatePosition();
		updateAnimation();
		setAnimation();
	}
	
	public void render(Graphics2D g2d) {
		g2d.drawImage(playerAnimations[playerAction][animationIndex], 
				(int)x, (int)y, 100, 90, null);
	}

	
	private void updateAnimation() {
		animationTick++;
		if(animationTick >= animationSpeed) {
			animationTick = 0;
			animationIndex++;
			if(animationIndex >= getPlayerAmount(playerAction)) {
				animationIndex = 0;
				attacking = false;
			}
		}
	}
	
	private void setAnimation() {
		int startAnimation = playerAction;
		
		if(moving) {
			playerAction = RUNNING;
		}
		else {
			playerAction = PAUSE;
		}
		
		if(attacking) {
			playerAction = ATTACK_1;
		}
		
		if(startAnimation != playerAction) {
			animationTick = 0;
			animationIndex = 0;
		}
	}

	
	private void updatePosition() {
		moving = false;
		
		if(left && !right) {
			x -= playerSpeed;
			moving = true;
		}
		else if(right && !left) {
			x += playerSpeed;
			moving = true;
		}
		
		if(up && !down) {
			y -= playerSpeed;
			moving = true;
		}
		else if (down && !up) {
			y += playerSpeed;
			moving = true;
		}
		
	}
	
	private void loadAnimations() {
		BufferedImage playerImage = LoadSave.LoadSprites("/player.png");
			
		playerAnimations = new BufferedImage[9][6];
		for(int col = 0; col < playerAnimations.length; col++) {
			for(int row = 0; row < playerAnimations[col].length; row++) {
				playerAnimations[col][row] = playerImage.getSubimage(row*64, col*40, 64, 40);
			}			
		}
	}
	
	public void resetDirectionBooleans() {
		left = false;
		right = false;
		up = false;
		down = false;
	}
	
	public void setAttack(boolean attacking) {
		this.attacking = attacking;
	}
	
	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isUp() {
		return up;
	}


	public void setUp(boolean up) {
		this.up = up;
	}


	public boolean isDown() {
		return down;
	}


	public void setDown(boolean down) {
		this.down = down;
	}
}
