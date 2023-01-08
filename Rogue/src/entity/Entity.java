package entity;

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
	public int knockback = 50;;
	public BufferedImage up1, up2, down1, down2, left1, left2, left3, left4, right1, right2, right3, right4;
	public BufferedImage atk1;
	public String direction;
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle hitBox = new Rectangle(5, 5, 38, 38);//5, 5, 38, 38
	public int hitBoxDefaultX, hitBoxDefaultY;
	public boolean collisionOn = false;
	public double speedD;
	public int actionLock = 0;
	public int hx, hy;

	public Entity(GamePanel gp) {
		this.gp = gp;
	}

	public void setAction() {}
	public void update() {
		
		setAction();
		
		collisionOn = false;
		gp.cDetect.checkTile(this, speed);
		gp.cDetect.checkObject(this, false);
		gp.cDetect.checkPlayer(this);
	
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + (gp.tileSize)> gp.player.worldX - gp.player.screenX && 
		   worldX - (gp.tileSize) < gp.player.worldX + gp.player.screenX && 
		   worldY + (gp.tileSize) > gp.player.worldY - gp.player.screenY && 
		   worldY - (gp.tileSize) < gp.player.worldY + gp.player.screenY) {
			gp.cDetect.checkTile(this,speed);
			gp.cDetect.checkObject(this, false);
			gp.cDetect.checkPlayer(this);
			if(!collisionOn) {
				
				switch(direction) {
				case "up":
				worldY -= speed; break;		
				case "down":
				worldY += speed; break;
				case "left": 
				worldX -= speed; break;
				case "right":
				worldX += speed; break;
				case "up right":
				worldY -= speed; worldX += speed; break;
				case "up left":
				worldY -= speed; worldX -= speed; break;
				case "down right":
				worldY += speed; worldX += speed; break;
				case "down left":
				worldY += speed; worldX -= speed; break;
					
				}
				
			}
			
			spriteCounter++;
			if(spriteCounter > 12) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}else if(spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
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
			case "atk1":
				image = atk1;
				break;
		
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
			g2.drawImage(image, screenX, screenY, null);
		}
	}
}
