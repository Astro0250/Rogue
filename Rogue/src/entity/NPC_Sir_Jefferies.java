package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.lang.Math;

import javax.imageio.ImageIO;

import main.GamePanel;
public class NPC_Sir_Jefferies extends Entity{
	
	public NPC_Sir_Jefferies(GamePanel gp) {
		
		super(gp);
		doesDamage = false;
		health = 10000;
		direction = "down";
		speed = 0;
		hitBox.x = 0;
		hitBox.y = 0;		
		hitBox.width = 48;
		hitBox.height = 48;
		getImage();
	}
	public void getImage() {
		down1 = setup("Sir_Jefferies");
		up1 = setup("Sir_Jefferies");
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
	public void setAction() {}
	public void doAction() {}
}
