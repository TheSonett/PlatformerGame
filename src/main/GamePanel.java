package main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import inputs.KeyInputs;
import inputs.MouseInputs;

public class GamePanel extends JPanel {
	private float xDelta = 100;
	private float yDelta = 100;
	private float xDir = 1f, yDir = 1f;
	private int frames = 0;
	private long lastCheck = 0;
	
	private Color color = new Color(150, 20, 90);
	private Random random;
	
	// temporary
	private ArrayList<MyRect> rects = new ArrayList<>();
	
	public GamePanel() {
		random = new Random();
		
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
	
	public void spawRect(int x, int y) {
		rects.add(new MyRect(x, y));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		
		for(MyRect rect : rects) {
			rect.updateRect();
			rect.draw(g);
		}
		
		
		updateReactangle();
		
		g2d.setColor(color);
		g2d.fillRect((int)xDelta, (int)yDelta, 200, 50);
		// repaint done in game thread/loop
	}
	
	
	private void updateReactangle() {
		xDelta += xDir;
		if(xDelta > 400 || xDelta < 0) {
			xDir *= -1;
			color = getRandomColor();
		}
		
		yDelta += yDir;
		if(yDelta > 400 || yDelta < 0) {
			yDir *= -1;
			color = getRandomColor();
		}
	}
	
	private Color getRandomColor() {
		int r = random.nextInt(255);
		int g = random.nextInt(255);
		int b = random.nextInt(255);
		
		return new Color(r, g, b);
	}
	
	
	
	
	// for multiple rectangles------------
	public class MyRect {
		int x, y, w, h;
		int xDir = 1, yDir = 1;
		Color color;
		
		public MyRect(int x, int y) {
			this.x = x;
			this.y = y;
			
			w = random.nextInt(50);
			h = w;
			color = newColor();
		}
		
		public void updateRect() {
			this.x += xDir;
			this.y += yDir;
			
			if((x + w) > 400 || x < 0) {
				xDir *= -1;
				color = newColor();
			}
			
			if((y + h) > 400 || y < 0) {
				yDir *= -1;
				color = newColor();
			}
		}
		
		private Color newColor() {
			return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
		}
		
		public void draw(Graphics g) {
			g.setColor(color);
			g.fillRect(x, y, w, h);
		}
	}//-----------------
	
	
	
	
	
	
	
	
}
