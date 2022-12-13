package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	public final int screenX;
	public final int screenY;

	public int hasKey = 0;

	public Player(GamePanel gp, KeyHandler keyH) {

		this.gp = gp;
		this.keyH = keyH;

		screenX = (gp.screenWidth / 2) - (gp.tileSize / 2);
		screenY = (gp.screenHeight / 2) - (gp.tileSize / 2);

		// HITBOX SETTINGS
		hitBox = new Rectangle();
		hitBox.x = 8;
		hitBox.y = 16;
		hitBoxDefaultX = hitBox.x;
		hitBoxDefaultY = hitBox.y;
		hitBox.width = 30;
		hitBox.height = 30;

		setDefaultValues();
		getPlayerImage();
	}

	public int worldX(int xcoord) {
		return worldX = xcoord - gp.tileSize;
	}

	public int worldY(int ycoord) {
		return worldY = ycoord - gp.tileSize;
	}
//	public int speedModifier(int percent) {
//		int speedModifier = 1;
//		if (percent > 0) {
//		return speedModifier = percent;
//		}
//		return speedModifier;
//	}

	public void setDefaultValues() {
		worldX(gp.tileSize * 14);
		worldY(gp.tileSize * 24);
		speed = 4;
		direction = "down";
	}

	public void getPlayerImage() {
		try {

			up1 = ImageIO.read(getClass().getResource("/player/Up1.png"));
			up2 = ImageIO.read(getClass().getResource("/player/Up2.png"));

			down1 = ImageIO.read(getClass().getResource("/player/Down1.png"));
			down2 = ImageIO.read(getClass().getResource("/player/Down2.png"));

			right1 = ImageIO.read(getClass().getResource("/player/Right1.png"));
			right2 = ImageIO.read(getClass().getResource("/player/Right2.png"));
			right3 = ImageIO.read(getClass().getResource("/player/Right3.png"));
			right4 = ImageIO.read(getClass().getResource("/player/Right4.png"));

			left1 = ImageIO.read(getClass().getResource("/player/Left1.png"));
			left2 = ImageIO.read(getClass().getResource("/player/Left2.png"));
			left3 = ImageIO.read(getClass().getResource("/player/Left3.png"));
			left4 = ImageIO.read(getClass().getResource("/player/Left4.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void update() {

		// CHECK TILE COLLISION
		collisionOn = false;
		gp.cDetect.checkTile(this);
		// CHECK OBJECT COLLISION
		int objIndex = gp.cDetect.checkObject(this, true);
		pickUpObject(objIndex);

		if ((keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed)) {
			if (keyH.upPressed) {
				direction = "up";
				if (spriteNum == 3 || spriteNum == 4) {
					spriteNum = 1;
				}
				if (!collisionOn) {
					worldY -= speed;
				}
			}
			if (keyH.downPressed) {
				direction = "down";
				if (spriteNum == 3 || spriteNum == 4) {
					spriteNum = 1;
				}
				if (!collisionOn) {
					worldY += speed;
				}
			}
			if (keyH.leftPressed) {
				direction = "left";
				if (!collisionOn) {
					worldX -= speed;
				}
			}
			if (keyH.rightPressed) {
				direction = "right";
				if (!collisionOn) {
					worldX += speed;
				}
			}

			spriteCounter++;
			if (spriteCounter > (36 / speed) && (direction.equals("left") || direction.equals("right"))) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 3;
				} else if (spriteNum == 3) {
					spriteNum = 4;
				} else if (spriteNum == 4) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}

			if (spriteCounter > (40 / speed) && !(direction.equals("left") || direction.equals("right"))) {
				if (spriteNum == 1) {
					spriteNum = 2;
				} else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
	}

	public void pickUpObject(int i) {
		if (i != 999) {

			String objectName = gp.obj[i].name;

			switch (objectName) {
			case "Key":
				gp.playSoundEffect(0);
				hasKey++;
				gp.obj[i] = null;
				gp.UI.showMessage("You got a key!");
				break;
			case "Door":
				gp.UI.showMessage("You need a Key!");
				if (hasKey > 0) {
					gp.playSoundEffect(0);
					gp.obj[i] = null;
					hasKey--;
					gp.UI.showMessage("*CLICK*");
				}
				break;
			case "SpeedPotion":
				gp.UI.showMessage("Speed Boost!");
				gp.playSoundEffect(0);
				speed += 2;
				gp.obj[i] = null;
				break;
			case "Chest":
				gp.UI.gameWin = true;
				gp.stopMusic();
				break;
			}
				
		}
	}

	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
		case "up":
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
			break;

		case "down":
			if (spriteNum == 1) {
				image = down2;
			}
			if (spriteNum == 2) {
				image = down1;
			}
			break;

		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
			if (spriteNum == 3) {
				image = right3;
			}
			if (spriteNum == 4) {
				image = right4;
			}
			break;

		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
			if (spriteNum == 3) {
				image = left3;
			}
			if (spriteNum == 4) {
				image = left4;
			}
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}
