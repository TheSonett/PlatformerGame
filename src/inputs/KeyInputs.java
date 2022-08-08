package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import static animations.PlayerActions.Directions.*;

public class KeyInputs implements KeyListener {
	private GamePanel gamePanel;
	
	public KeyInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
				gamePanel.setMovement(UP);
				break;
			case KeyEvent.VK_A:
				gamePanel.setMovement(LEFT);
				break;
			case KeyEvent.VK_S:
				gamePanel.setMovement(DOWN);
				break;
			case KeyEvent.VK_D:
				gamePanel.setMovement(RIGHT);
				break;
		}		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_W:
			case KeyEvent.VK_A:
			case KeyEvent.VK_S:
			case KeyEvent.VK_D:
				gamePanel.setMoving(false);
				break;
		}	
	}

}
