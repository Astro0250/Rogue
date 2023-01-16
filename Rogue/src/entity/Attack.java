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
	int soundCounter = 0;
	int spriteDelay = 0;
	public Attack(GamePanel gp) {
		super(gp);
		direction = gp.player.direction;
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
		direction = gp.player.direction;
		getImage();
		hitBox = new Rectangle();
		hitBox.x = 8;
		hitBox.y = 16;
		hitBoxDefaultX = gp.atk.worldX;
		hitBoxDefaultY = gp.atk.worldY;
		hitBox.width = 30;
		hitBox.height = 30;
		gp.atk.worldX = a;
		gp.atk.worldY = b;
	}
	//important for the cooldown/duration
	public boolean z = false;

	public void setAttack() {
		int duration = 31;
		int cooldown = 62;
		int delay = gp.delay();
		if (delay >= cooldown || z) {
			if (gp.keyH.spacePressed || z) {
				if(soundCounter == 0) {
					gp.playSoundEffect(0);
					soundCounter++;
				}
				//System.out.println(gp.cDetect.collisionIndex());
				if (delay >= cooldown) {
					gp.delay(0);
					delay = gp.delay();
					z = true;
				}
				int Col = (int) gp.cDetect.tilePositionUpon(gp.player).get(0);
				int Row = (int) gp.cDetect.tilePositionUpon(gp.player).get(1);
//				hitBox.x = gp.atk[0].worldX;
//				hitBox.y = gp.atk[0].worldY;
				
				direction = gp.player.direction;
				gp.cDetect.checkAttack(this, gp.npc, 1);
				boolean hit = gp.cDetect.collision();
				//System.out.println(hit);
				//System.out.println("hit");
				if (gp.player.direction.equals("up")) {
				
					gp.atk.worldX = Col;
					gp.atk.worldY =  (int) (Row - gp.tileSize * 1.3);
					
					if (hit) {
						if(!(gp.cDetect.checkKnockback(gp.npc[gp.cDetect.collisionIndex()], "up", gp.player))) {
							gp.npc[gp.cDetect.collisionIndex()].worldY -= gp.player.knockback;
							collisionOn = false;

						}
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						gp.playSoundEffect(2);
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					}
					
				}
				if (gp.player.direction.equals("down")) {
			
					gp.atk.worldX = Col;
					gp.atk.worldY = (int) (Row + gp.tileSize);
					
					if (hit) {
						if(!(gp.cDetect.checkKnockback(gp.npc[gp.cDetect.collisionIndex()], "down", gp.player))) {
							gp.npc[gp.cDetect.collisionIndex()].worldY += gp.player.knockback;
							collisionOn = false;

						}
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						gp.playSoundEffect(2);
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					
					}
				}
				if (gp.player.direction.equals("left")) {
					
					gp.atk.worldX = (int) (Col - gp.tileSize * 1.3);
					gp.atk.worldY = Row;
					
					if (hit) {
						if(!(gp.cDetect.checkKnockback(gp.npc[gp.cDetect.collisionIndex()], "left", gp.player))) {
							gp.npc[gp.cDetect.collisionIndex()].worldX -= gp.player.knockback;
							collisionOn = false;
						}
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						gp.playSoundEffect(2);
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					
					}
				}

				if (gp.player.direction.equals("right")) {// gp.tileSize
					gp.atk.worldX = Col + gp.tileSize;
					gp.atk.worldY = Row;
					
					if (hit) {
						if(!(gp.cDetect.checkKnockback(gp.npc[gp.cDetect.collisionIndex()], "right", gp.player))) {
							gp.npc[gp.cDetect.collisionIndex()].worldX += gp.player.knockback;
							collisionOn = false;
						}
						gp.npc[gp.cDetect.collisionIndex()].health -= 10;
						gp.playSoundEffect(2);
						System.out.println("Enemy Health - " + gp.npc[gp.cDetect.collisionIndex()].health);
					
					}
				}

				if (delay >= duration) {
					z= false;
					gp.atk = new Attack(gp);
					soundCounter = 0;
					
				}
				//System.out.println(gp.atk[0].worldX);
				hitBox.x = gp.atk.worldX;
				hitBox.y = gp.atk.worldY;
			}

		}
	}
	public BufferedImage setup(String imageName) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/attacks/playerAtk"+imageName+".png"));
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
		up1 = setup("Up1");
		up2 = setup("Up2");
		up3 = setup("Up3");
		down1 = setup("Down1");
		down2 = setup("Down2");
		down3 = setup("Down3");
		right1 = setup("Right1");
		right2 = setup("Right2");
		right3 = setup("Right3");
		left1 = setup("Left1");
		left2 = setup("Left2");
		left3 = setup("Left3");
		
	}
	public void draw(Graphics2D g2) {
		//System.out.println(spriteNum);
		
		BufferedImage image = null;
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		direction = gp.player.direction;
		int delay = gp.delay();
		
		
		//Use duration/3 values 
		
		if(delay <= 10) {
			//If you want attack in reverse order switch these variables
			spriteNum = 1;
		}
		if(delay <= 20 && delay > 10) {
			
			spriteNum = 2;
		}
		if(delay <= 30 && delay > 20) {
			spriteNum = 3;
		}
		
		switch (direction) {
		case "right":
			
			if (spriteNum == 1) {
				
				image = right1;
			}else if (spriteNum == 2) {
				
				image = right2;
			}else if (spriteNum == 3) {
				
				image = right3;
			}
			break;

		case "left":
			
			if (spriteNum == 1) {
				image = left1;
			}else if (spriteNum == 2) {
				image = left2;
			}else if (spriteNum == 3) {
				image = left3;
			}
			break;
		case "up":
			
			if (spriteNum == 1) {
				image = up1;
			}else if (spriteNum == 2) {
				image = up2;
			}else if (spriteNum == 3) {
				image = up3;
			}
			break;

		case "down":
			
			if (spriteNum == 1) {
				image = down1;
			}else if (spriteNum == 2) {
				image = down2;
			}else if (spriteNum == 3) {
				image = down3;
			}
			break;
		}
	
		spriteDelay++;
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
	}
	
}
