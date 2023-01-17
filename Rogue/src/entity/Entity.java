package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import main.GamePanel;


public class Entity {

	GamePanel gp;
	public int worldX;
	public int worldY;	
	public int speed;
	public int health = 100;
	public int knockback = 50;
	public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, left4, right1, right2, right3, right4;
	public BufferedImage atk1;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle hitBox = new Rectangle(5, 5, 38, 38);//5, 5, 38, 38
	public Rectangle hitBoxE = new Rectangle(5, 5, 38, 38);//5, 5, 38, 38
	public int hitBoxDefaultX, hitBoxDefaultY;
	public boolean collisionOn = false;
	public boolean doesDamage = true;
	public double speedD;
	public int actionLock = 0;
	int dialogueSet = 1;
	String dialogue[] = new String[20];
	int dialogueIndex = 0;
	

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {}
	public void speak() {}
	public void doAction() {}
	public void update() {
		if (health > 0) {
			setAction();
			doAction();
		} else {
			worldX = gp.tileSize*29;
			worldY = gp.tileSize*28;
			if (actionLock < 1) {
				gp.player.kills++;
				actionLock++;
			}
		}
	}
	public void draw(Graphics2D g2) {
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + (gp.tileSize)> gp.player.worldX - gp.player.screenX && 
		   worldX - (gp.tileSize) < gp.player.worldX + gp.player.screenX && 
		   worldY + (gp.tileSize) > gp.player.worldY - gp.player.screenY && 
		   worldY - (gp.tileSize) < gp.player.worldY + gp.player.screenY) {

			
			switch (direction) {
			case "right":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;

			case "left":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;
			case "up":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;

			case "down":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;
			case "up right":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;
			case "up left":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;
			case "down right":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;
			case "down left":
				if (spriteNum == 1) {
					image = down1;
				}
				if (spriteNum == 2) {
					image = up1;
				}
				break;
			}
			// Health Bar
			if(!direction.equals("atk1") && this.health < 100) {
				g2.setColor(Color.black);
				g2.fillRect(screenX-2, screenY-23, 54, 15);
				g2.setColor(Color.red);
				g2.fillRect(screenX, screenY-20,this.health/2, 10);
				
			}
			//draws sprite
			g2.drawImage(image, screenX, screenY, null);
		}
	}

}
