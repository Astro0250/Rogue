package main;

import java.awt.event.*;

import main.GamePanel;
import entity.Player;
import tile.TileManager;

public class KeyHandler implements KeyListener {

	public boolean upPressed, downPressed, rightPressed, leftPressed, shiftPressed;

	// DEBUG
	boolean checkDrawTime = false;
	
	@Override
	public void keyTyped(KeyEvent e) {
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_SPACE) {
			if(TileManager.tileSet == 1) {
				TileManager.tileSet--;
			} else {
				TileManager.tileSet++;
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		
		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = true;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_SHIFT) {
			shiftPressed = true;
		}
		
		// DEBUG
		if (code == KeyEvent.VK_T) {
			if(checkDrawTime) {
				checkDrawTime = false;
				} else {
				checkDrawTime = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_SHIFT || code == KeyEvent.VK_SHIFT) {
			shiftPressed = false;
		}
	}

}

