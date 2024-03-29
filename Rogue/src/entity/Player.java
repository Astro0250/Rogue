package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import tile.TileManager;
import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

	KeyHandler keyH;
	private String facing;
	public final int screenX;
	public final int screenY;
	private int knockAmt;
	public int keyCount = 0;
	public BufferedImage classified;
	int temp;
	int kills = 0;

	public int knockAmt() {
		return knockAmt;
	}

	public void knockAmt(int a) {
		knockAmt = a;
	}

	public String facing() {
		return facing;
	}

	public void facing(String a) {
		facing = a;
	}

	public Player(GamePanel gp, KeyHandler keyH) {

		super(gp);
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

	public double speedModifier(int Tile) {
		double speedModifier = 1;
		if (!(null == TileManager.getSpeed().get(Tile))) {
			speedModifier = TileManager.getSpeed().get(Tile);
		}
		return speedModifier;
	}

	public void setDefaultValues() {
		worldX(gp.tileSize * 42);
		worldY(gp.tileSize * 154);
		health = 100;
		speed = 8;
		direction = "down";
		knockback = 100;
	}

	public void getPlayerImage() {
		try {
			classified = ImageIO.read(getClass().getResource("/CLASSIFIED_DOCUMENTS/CLASSIFIED.png"));
			
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

	// marginally beautify knockback
	public void knockback(String axis, int amount) {
		// Change this variable if no likey
		int occurences = 25;
		// Holder
		//knockAmt(amount / occurences);
		if ("worldX".equals(axis)) {
			for (int a = 0; a < occurences; a++) {
				worldX += amount / occurences;
			}
		}
		if ("worldY".equals(axis)) {
			for (int a = 0; a < occurences; a++) {
				worldY += amount / occurences;
			}
		}
	}

	public void update() {
		// CHECK TILE COLLISION
		collisionOn = false;

		// CHECK OBJECT COLLISION
		int objIndex = gp.cDetect.checkObject(this, true);
		interactObject(objIndex);

		// CHECK ENTITY COLLISION
		int npcIndex = gp.cDetect.checkEntity(this, gp.npc, speedD);
		// System.out.println(npcIndex);
		interactNPC(npcIndex);

		speedD = speed * speedModifier(gp.cDetect.tileStoodUpon(this));
		if (keyH.shiftPressed) {
			speedD *= 1.25;
		}
		int speedpy = (int) (speedD / 1.42);

		if ((keyH.upPressed || keyH.downPressed || keyH.rightPressed || keyH.leftPressed)) {
			if (keyH.downPressed && keyH.upPressed) {
			} else if (keyH.downPressed) {
				direction = "down";
				if (spriteNum == 3 || spriteNum == 4) {
					spriteNum = 1;
				}

				gp.cDetect.checkTile(this, speedD);
				gp.cDetect.checkObject(this, true);
				gp.cDetect.checkEntity(this, gp.npc, speedD);

				if (!collisionOn) {
					if (keyH.leftPressed || keyH.rightPressed) {
						worldY += (speedpy);
					} else {
						worldY += speedD;
					}
				}
			} else if (keyH.upPressed) {

				direction = "up";
				if (spriteNum == 3 || spriteNum == 4) {
					spriteNum = 1;
				}
				gp.cDetect.checkTile(this, speedD);
				gp.cDetect.checkObject(this, true);
				gp.cDetect.checkEntity(this, gp.npc, speedD);

				if (!collisionOn) {
					if (keyH.rightPressed || keyH.leftPressed) {
						worldY -= (speedpy);
					} else {
						worldY -= speedD;
					}
				}
			}
			if (keyH.rightPressed && keyH.leftPressed) {
			} else if (keyH.rightPressed) {
				direction = "right";
				gp.cDetect.checkTile(this, speedD);
				gp.cDetect.checkObject(this, true);
				gp.cDetect.checkEntity(this, gp.npc, speedD);

				if (!collisionOn) {
					if (keyH.upPressed || keyH.downPressed) {
						worldX += speedpy;
					} else {
						worldX += speedD;
					}
				}

			} else if (keyH.leftPressed) {
				direction = "left";
				gp.cDetect.checkTile(this, speedD);
				gp.cDetect.checkObject(this, true);
				gp.cDetect.checkEntity(this, gp.npc, speedD);

				if (!collisionOn) {
					if (keyH.downPressed || keyH.upPressed) {
						worldX -= (speedpy);
					} else {
						worldX -= speedD;
					}
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

	public void interactObject(int i) {
		if (i != 999) {
			switch(gp.obj[i].name) {
				case"Chest":{gp.gameState = gp.classState;}
				case"GateHor":{
					if(keyCount > 0 && gp.obj[i].collision) {
						gp.obj[i].collision = false;
						gp.obj[i].image = gp.obj[i].image2;
						gp.playSoundEffect(4);
						keyCount--;
					}
				}
				case"GateVertLR":{
					if(keyCount > 0 && gp.obj[i].collision) {
						gp.obj[i].collision = false;
						gp.obj[i].image = gp.obj[i].image2;
						gp.playSoundEffect(4);
						keyCount--;
					}
				}
				case"GateVertRL":{
					if(keyCount > 0 && gp.obj[i].collision) {
						gp.obj[i].collision = false;
						gp.obj[i].image = gp.obj[i].image2;
						gp.playSoundEffect(4);
						keyCount--;
					}
				}
			}	
		}
	}

	public void interactNPC(int i) {
		collisionOn = false;
		if (i != 999) {
			if(gp.npc[i].doesDamage) {
				health -= 10;
				switch (gp.npc[i].direction) {
				case "up": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldY", -gp.npc[i].knockback);
					}
				}
					break;
				case "down": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldY", gp.npc[i].knockback);
					}
				}
					break;
				case "left": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldX", -gp.npc[i].knockback);
					}
				}
					break;
				case "right": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldX", gp.npc[i].knockback);
					}
				}
					break;
	
				case "up right": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldY", -gp.npc[i].knockback);
						knockback("worldX", gp.npc[i].knockback);
					}
				}
					break;
				case "up left": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldY", -gp.npc[i].knockback);
						knockback("worldX", -gp.npc[i].knockback);
					}
				}
					break;
				case "down left": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldY", gp.npc[i].knockback);
						knockback("worldX", -gp.npc[i].knockback);
					}
				}
					break;
				case "down right": {
					if (!(gp.cDetect.checkKnockback(this, gp.npc[i].direction, this))) {
						knockback("worldY", gp.npc[i].knockback);
						knockback("worldX", gp.npc[i].knockback);
					}
				}
					break;
				}
				if (health < 0) {
					health = 0;
				}
				//System.out.println("health = " + health);
				gp.playSoundEffect(2);
			} else {
				switch(direction) {
				case"up" -> worldY += speed*2;
				case"down" -> worldY -= speed*2;
				case"left" -> worldX += speed*2;
				case"right" -> worldX -= speed*2;
				}
				if(kills >= 2) {
					//System.out.println(kills);
					gp.npc[i] = new NPC_Sir_Jefferies(gp, 3);;
					gp.npc[i].worldX = gp.tileSize * 108;
					gp.npc[i].worldY = gp.tileSize * 120;
					kills = 0;
				}
				gp.gameState = gp.dialogueState;
				gp.npc[i].speak();
				if((gp.npc[i].dialogueIndex == 1 && gp.npc[i].dialogueSet == 1) || (gp.npc[i].dialogueIndex == 6 && gp.npc[i].dialogueSet == 2) || (gp.npc[i].dialogueIndex == 2 && gp.npc[i].dialogueSet == 3)) {
					keyCount++;
				}
			}
		}
	}
	public void draw(Graphics2D g2) {

		BufferedImage image = null;

		switch (direction) {
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
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

	}
}
