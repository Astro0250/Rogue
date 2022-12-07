package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import item.Key;

public class UserInterface {
	GamePanel gp;
	Font counterFont, messageFont, winFont;
	BufferedImage keyImage;
	public boolean messageOn;
	public String message = "";
	int messageCounter;
	public boolean gameWin;

	public UserInterface(GamePanel gp) {
		this.gp = gp;
		counterFont = new Font("Ariel", Font.PLAIN, 50);
		messageFont = new Font("Ariel", Font.ITALIC, 30);
		winFont = new Font("Ariel", Font.BOLD, 80);
		Key key = new Key();
		keyImage = key.image;
	}

	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		if (gameWin) {

			g2.setFont(winFont);
			g2.setColor(Color.YELLOW);

			String text = "";
			int textLength;
			int x;
			int y;

			text = "YOU WIN!";
			textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

			x = gp.screenWidth / 2 - textLength / 2;
			y = gp.screenHeight / 2 - 100;
			
			g2.drawString(text, x, y);
			
			gp.player.direction = "down";

			gp.stopGameThread();

		} else {
			g2.setFont(counterFont);
			g2.setColor(Color.BLACK);
			g2.drawString("= " + gp.player.hasKey, 74, 50);
			g2.drawImage(keyImage, gp.tileSize / 2, 10, gp.tileSize, gp.tileSize, null);

			// MESSAGE
			if (messageOn) {

				g2.setFont(messageFont);
				g2.setColor(Color.yellow);
				g2.drawString(message, gp.tileSize * 2, gp.tileSize * 5);

				messageCounter++;

				if (messageCounter > 80) {
					messageCounter = 0;
					messageOn = false;
				}
			}
		}
	}
}
