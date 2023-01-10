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
	public boolean z = false;

	public void setAttack() {
		int duration = 31;
		int cooldown = 62;
		int delay = gp.delay();
		if (delay >= cooldown || z) {
			if (gp.keyH.spacePressed || z) {

				if (delay >= cooldown) {
					gp.delay(0);
					delay = gp.delay();
					z = true;
				}
				gp.atk[0] = new Attack(gp);
				int Col = (int) gp.cDetect.tilePositionUpon(gp.player).get(0);
				int Row = (int) gp.cDetect.tilePositionUpon(gp.player).get(1);
				gp.atk[0] = new Attack(gp);
				hitBox.x = hx;
				hitBox.y = hy;
				direction = "atk1";
				gp.cDetect.checkEntity(this, gp.npc, 1.0);
				boolean hit = gp.cDetect.collision();

				if (gp.player.direction.equals("up")) {
					hx = Col;
					hy = (int) (Row - gp.tileSize * 1.3);
					gp.atk[0].worldX = hx;
					gp.atk[0].worldY = hy;

					if (hit) {
						gp.npc[gp.cDetect.collisionIndex()].worldY -= gp.player.knockback;
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					}
				}
				if (gp.player.direction.equals("down")) {
					hx = Col;
					hy = (int) (Row + gp.tileSize);
					gp.atk[0].worldX = hx;
					gp.atk[0].worldY = hy;
					if (hit) {
						gp.npc[gp.cDetect.collisionIndex()].worldY += gp.player.knockback;
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					}
				}
				if (gp.player.direction.equals("left")) {
					hx = (int) (Col - gp.tileSize * 1.3);
					hy = Row;
					gp.atk[0].worldX = hx;
					gp.atk[0].worldY = hy;
					if (hit) {
						gp.npc[gp.cDetect.collisionIndex()].worldX -= gp.player.knockback;
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					}
				}

				if (gp.player.direction.equals("right")) {// gp.tileSize
					hx = Col + gp.tileSize;
					hy = Row;
					gp.atk[0].worldX = hx;
					gp.atk[0].worldY = hy;
					if (hit) {
						gp.npc[gp.cDetect.collisionIndex()].worldX += gp.player.knockback;
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					}
				}

				if (delay >= duration) {
					z= false;
					gp.atk[0] = new Attack(gp);
				}

			}

		}
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
