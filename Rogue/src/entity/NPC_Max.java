package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class NPC_Max extends Entity{
	
	public NPC_Max(GamePanel gp) {
		
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
		
		actionLock++;
		if (actionLock == 60) {
			
			Random random = new Random();
			int i = random.nextInt(100)+1;
			if (i<=25) {
				direction = "up";
			}else if(i<=50){
				direction = "down";
			}else if(i<=75) {
				direction = "left";
			}else {
				direction = "right";
			}
			
			actionLock = 0;
		}
	}
	public void bounce(boolean bounce) {
		String[] move = new String[] {"up", "down", "left", "right"};
		
	}
}
