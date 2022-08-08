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

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	private float xDelta = 100, yDelta = 100;
	private BufferedImage player, subImage;

	
	public GamePanel() {
		this.setPreferredSize(new Dimension(1280, 800));
		
		importImage();
		
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
	
	
	public void setRectPos(int xDelta, int yDelta) {
		this.xDelta = xDelta;
		this.yDelta = yDelta;
	}
	
	public void changeXDelta(int xDelta) {
		this.xDelta += xDelta;
	}
	
	public void changeYDelta(int yDelta) {
		this.yDelta += yDelta;
	}
	

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		subImage = player.getSubimage(1*64, 8*40, 64, 40);
		g2d.drawImage(subImage, (int)xDelta, (int)yDelta, 128, 80, null);
		
		// repaint done in game thread/loop
	}
}
