package main;

import java.awt.Graphics;
import java.awt.Graphics2D;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {
	
	private GameWindow gameWindow;
	private GamePanel gamePanel;
	private Thread gameLoop;
	private Player player;
	private LevelManager levelManager;
	
	private final int FPS = 120;
	private final int UPS = 200;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 1.5f;
	public final static int TILES_WIDTH = 26;
	public final static int TILES_HEIGHT = 14;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_HEIGHT;
	
	
	public Game() {
		initGame();
		
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);
		
		startGameLoop();
	}
	
	public void initGame() {
		player = new Player(200, 200);
		levelManager = new LevelManager(this);
	}
	
	private void startGameLoop() {
		gameLoop = new Thread(this);
		gameLoop.start();
	}
	
	public void update() {
		player.update();
		levelManager.update();
	}
	
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		levelManager.render(g2d);
		player.render(g2d);
	}
	
	@Override
	public void run() {
		double timePerFrame = 1000000000.0 / FPS;
		double timePerUpdate = 1000000000.0 / UPS;
	
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			if(deltaU >= 1) {
				update(); // calling update per second
				updates++;
				deltaU--;
			}
			
			if(deltaF >= 1) {
				gamePanel.repaint();
				frames++;
				deltaF--;
			}
			
			
			if(System.currentTimeMillis() - lastCheck >= 1000) {
				lastCheck = System.currentTimeMillis();
				System.out.println("FPS: " + frames + " | UPS " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	public void windowFocusLost() {
		player.resetDirectionBooleans();
	}
	
	public Player getPlayer() {
		return this.player;
	}
}
