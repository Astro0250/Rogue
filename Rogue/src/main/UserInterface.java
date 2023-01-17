package main;

import java.awt.BasicStroke;
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
	public String currentDialogue = "";
	long startTime = System.nanoTime();
	int counter = 0;

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
		
		if (gp.gameState == gp.playState) {
			drawPlay();
		}
		if (gp.gameState == gp.pauseState) {
			drawPause();
		}
		if (gp.gameState == gp.deathState) {
			drawDead();
		}
		if (gp.gameState == gp.dialogueState) {
			drawDialogue();
		}
		
	}
	public void drawClassifiedDoc(Graphics2D g2) {
		if(counter == 0) {
		String a = "" + (System.nanoTime()-startTime)/1000000000;
		System.out.println("Your Time Was - " + a + " seconds");
		counter++;
		}
		g2.drawImage(gp.player.classified, gp.screenWidth/2-388/2, gp.screenHeight/2-500/2, null);
	}
	public void drawDialogue() {
		// WINDOW 
		int x = gp.tileSize;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - x*2;
		int height = gp.tileSize*5;
		drawSubWindow(x, y, width, height);
		
		x += gp.tileSize;
		y += gp.tileSize*2;
		g2.setFont(new Font("Ariel", Font.BOLD, 35));
		
		for(String line : currentDialogue.split("\n")) {
			g2.drawString(line, x, y);
			y+= 40;
		}		
	}
	public void drawSubWindow(int x, int y, int width, int height) {
		
		Color PB = new Color(16, 7, 18, 220);
		g2.setColor(PB);
		g2.fillRoundRect(x, y, width, height, 35, 35);
		
		g2.setColor(Color.white);
		g2.setStroke(new BasicStroke(4));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	public void drawPause() {
		g2.setFont(Ariel_80_Bold);
		g2.setColor(Color.GRAY);
		String text = "Paused";
		int x = getXcenterText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public void drawPlay() {
		//dg2.drawRect(10, 10, 100, 20);
		g2.setColor(Color.black);
		g2.fillRect(10, 10, 210, 50);
		g2.setColor(Color.red);
		g2.fillRect(15, 15, gp.player.health*2, 40);
	}
	public void drawDead() {
		g2.setFont(Ariel_80_Bold);
		g2.setColor(new Color(113, 0, 0));
		String text = "YOU DIED";
		int x = getXcenterText(text);
		int y = gp.screenHeight/2;
		
		g2.drawString(text, x, y);
	}
	public int getXcenterText(String a) {
		int x = (gp.screenWidth/2) - ((int) g2.getFontMetrics().getStringBounds(a, g2).getWidth()/2);
		return x;
	}
}
