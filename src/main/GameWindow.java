package main;

import javax.swing.JFrame;

public class GameWindow extends JFrame{
	public GameWindow(GamePanel gamePanel) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(gamePanel);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
	}
}
