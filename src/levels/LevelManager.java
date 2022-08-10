package levels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Game;
import utils.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] levelSprite;
	private Level levelOne;
	
	public LevelManager(Game game) {
		this.game = game;
		
		importWorldSprite();
		levelOne = new Level(LoadSave.GetLevelData());
	}
	
	private void importWorldSprite() {
		BufferedImage image = LoadSave.LoadSprites("/world.png");
		levelSprite = new BufferedImage[48];
		for(int j = 0; j < 4; j++) {
			for(int i = 0; i < 12; i++) {
				int index = j * 12 + i;
				levelSprite[index] = image.getSubimage(i * 32, j * 32, 32, 32);
			}
		}
	}
	
	
	public void render(Graphics2D g2d) {
		for(int j = 0; j < Game.TILES_HEIGHT; j++) {
			for(int i = 0; i < Game.TILES_WIDTH; i++) {
				int index = levelOne.getSpriteIndex(i, j);
				g2d.drawImage(levelSprite[index], 
						i * Game.TILES_SIZE, j * Game.TILES_SIZE, 
						Game.TILES_SIZE, Game.TILES_SIZE, null);
			}
		}
	}
	
	public void update() {
		
	}
}
