package item;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Item {
	
	public BufferedImage image, image2;
	public String name;
	public boolean collision = false;
	public int worldX, worldY;
	public Rectangle hitBox = new Rectangle(0,0,48,48);
	public int hitBoxDefaultX = 0;
	public int hitBoxDefaultY = 0;
	public int sizeX = 48;
	public int sizeY = 48;
	
	public void draw(Graphics2D g2, GamePanel gp) {
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + (gp.tileSize)> gp.player.worldX - gp.player.screenX && 
		   worldX - (gp.tileSize) < gp.player.worldX + gp.player.screenX && 
		   worldY + (gp.tileSize) > gp.player.worldY - gp.player.screenY && 
		   worldY - (gp.tileSize) < gp.player.worldY + gp.player.screenY) {

			g2.drawImage(image, screenX, screenY, sizeX, sizeY, null);
		}
	}
}
