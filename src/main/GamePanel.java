package main;

import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import inputs.KeyInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private int xDelta = 100;
	private int yDelta = 100;
	
	
	public GamePanel() {
		addKeyListener(new KeyInputs(this));
		addMouseListener(new MouseInputs(this));
		addMouseMotionListener(new MouseInputs(this));
	}
	
	
	public void changeXDelta(int xDelta) {
		this.xDelta += xDelta;
	}
	
	public void changeYDelta(int yDelta) {
		this.yDelta += yDelta;
	}
	
	public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
	}
	
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.fillRect(xDelta, yDelta, 200, 50);
		
		repaint();
	}
}
