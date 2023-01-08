package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.lang.Math;

import javax.imageio.ImageIO;

import main.GamePanel;
//Collision doesn't work on one length crawlspace due to hitbox
public class NPC_Enemy extends Entity{
	
	public NPC_Enemy(GamePanel gp) {
		
		super(gp);
		
		direction = "down";
		speed = 2;
		
		getImage();
	}
	public void getImage() {
		down1 = setup("Max1");
		up1 = setup("Max2");
	}
	public BufferedImage setup(String imageName) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/NPC/"+imageName+".png"));
			BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, image.getType());
			Graphics2D g2 = scaledImage.createGraphics();
			g2.drawImage(image, 0, 0, gp.tileSize, gp.tileSize, null);
			image = scaledImage;
			g2.dispose();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}
	public void setAction() {
		
//		actionLock++;
//		if (actionLock == 60) {
			
//			Random random = new Random();
//			int i = random.nextInt(100)+1;
			//buffer can potentially be raised/lowered
			String d = "";
			if (Math.abs(gp.player.worldY  - worldY) <= 2) {
				worldY = gp.player.worldY;
			}
			if (Math.abs(gp.player.worldX  - worldX) <= 2) {
				worldX = gp.player.worldX;
			}
			System.out.println(gp.player.worldX + " " + gp.player.worldY + " " + worldX + " " +  worldY);
			if (gp.player.worldY < worldY) {
				d = "up";
			}else if(gp.player.worldY > worldY){
				d = "down";
			}
		
			if(gp.player.worldX < worldX) {
				
				d = "left";
				
				if(gp.player.worldY < worldY) {
					d = "up left";
				} else if(gp.player.worldY > worldY) {
					d = "down left";
				}
			}else if(gp.player.worldX > worldX){
			
				d = "right";
				
				if(gp.player.worldY < worldY) {
					d = "up right";
				} else if(gp.player.worldY > worldY) {
					d = "down right";
				}
			}
			direction = d;
			
//			actionLock = 0;
//		}
	}
	
}
