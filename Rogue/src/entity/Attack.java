package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.awt.Rectangle;

import main.GamePanel;


import java.util.Random;

import javax.imageio.ImageIO;



import javax.imageio.ImageIO;



public class Attack extends Entity{
	public Attack(GamePanel gp) {
		super(gp);
		direction = "atk1";
		getImage();
		hitBox = new Rectangle();
		hitBox.x = 8;
		hitBox.y = 16;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 30;
		hitBox.height = 30;
	}
	public Attack(GamePanel gp, int a, int b) {
		super(gp);
		direction = "atk1";
		getImage();
		hitBox = new Rectangle();
		hitBox.x = 8;
		hitBox.y = 16;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 30;
		hitBox.height = 30;
		gp.atk[0].worldX = a;
		gp.atk[0].worldY = b;
	}

	public BufferedImage setup(String imageName) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/attacks/"+imageName+".png"));
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
	public void getImage() {
		atk1 = setup("Basic");
	}
	
}
