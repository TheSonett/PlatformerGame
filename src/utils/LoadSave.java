package utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import main.Game;

public class LoadSave {
	public static BufferedImage LoadSprites(String fileName) {
		BufferedImage image = null;
		InputStream is = LoadSave.class.getResourceAsStream(fileName);
		try {
			image = ImageIO.read(is);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				is.close(); // free the resources
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return image;
	}
	
	public static int[][] GetLevelData() {
		int[][] levelData = new int[Game.TILES_HEIGHT][Game.TILES_WIDTH];
		BufferedImage image = LoadSave.LoadSprites("/level_one_data.png");
		
		
		for(int j = 0; j < image.getHeight(); j++) {
			for(int i = 0; i < image.getWidth(); i++) {
				Color color = new Color(image.getRGB(i, j));
				int value = color.getRed();
				if(value >= 48) {
					value = 0;
				}
				levelData[j][i] = value;
			}
		}
		
		return levelData;
	}
}
