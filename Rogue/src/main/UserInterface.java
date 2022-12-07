package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import item.Key;

public class UserInterface {
	GamePanel gp;
	Font christianIsDumb;
	BufferedImage keyImage;
	public boolean messageOn;
	public String message = "";

	public UserInterface(GamePanel gp) {
		this.gp = gp;
		christianIsDumb = new Font("Ariel", Font.PLAIN, 50);
		Key key = new Key();
		keyImage = key.image;
	}

	public void showMessage(String text) {
		
		message = text;
		messageOn = true;
	}

	public void draw(Graphics2D g2) {

		g2.setFont(christianIsDumb);
		g2.setColor(Color.BLACK);
		g2.drawString("= " + gp.player.hasKey, 74, 50);
		g2.drawImage(keyImage, gp.tileSize / 2, 10, gp.tileSize, gp.tileSize, null);

		if(messageOn) {
			g2.drawString(message, gp.screenWidth, gp.screenHeight/2);
		}
	}
}
