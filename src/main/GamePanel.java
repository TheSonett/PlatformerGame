package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyInputs;
import inputs.MouseInputs;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	
	private Game game;
	
	public GamePanel(Game game) {
		this.setPreferredSize(new Dimension(1280, 800));
		this.game = game;
		
		addKeyListener(new KeyInputs(this));
		addMouseListener(new MouseInputs(this));
		addMouseMotionListener(new MouseInputs(this));
	}

	
	public void updateGame() {
		
	}

	public Game getGame() {
		return this.game;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g);
	}
	
}
