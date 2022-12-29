package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import item.Key;

public class UserInterface {
	GamePanel gp;
	Graphics2D g2;
	Font Ariel_50, Ariel_30_Italic, Ariel_80_Bold;
//	BufferedImage keyImage;
	public boolean messageOn;
	public String message = "";
	int messageCounter;
	public boolean gameWin;

	public UserInterface(GamePanel gp) {
		this.gp = gp;
		Ariel_50 = new Font("Ariel", Font.PLAIN, 50);
		Ariel_30_Italic = new Font("Ariel", Font.ITALIC, 30);
		Ariel_80_Bold = new Font("Ariel", Font.BOLD, 80);
//		Key key = new Key();
//		keyImage = key.image;
	}

	public void showMessage(String text) {

		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {
		
		this.g2 = g2;
		
		g2.setFont(Ariel_80_Bold);
		g2.setColor(Color.GRAY);
		
		if (gp.gameState == gp.playState) {
			// SAVING THIS FOR LATER
		}
		if (gp.gameState == gp.pauseState) {
			drawPause();
		}
	}
	public void drawPause() {
		String text = "PAUSED";
		int x = getXcenterText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public int getXcenterText(String a) {
		int x = (gp.screenWidth/2) - ((int) g2.getFontMetrics().getStringBounds(a, g2).getWidth()/2);
		return x;
	}
}
