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
	
	public NPC_Sir_Jefferies(GamePanel gp, int hogtie) {
		
		super(gp);
		dialogueSet = hogtie; // called it this so i don't accidentally use it
		doesDamage = false;
		health = 10000;
		direction = "down";
		speed = 0;
		hitBox.x = 0;
		hitBox.y = 0;		
		hitBox.width = 48;
		hitBox.height = 48;
		setDialogue();
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
	public void setDialogue(){
		if(dialogueSet == 1) {
			dialogue[0] = "Hey! You There!";
			dialogue[1] = "Here's a key to that gate over there.";
			dialogue[2] = "It would do wonders for me \nif you could open that up.";
			dialogue[3] = "Go on!";
			dialogue[4] = "What are you waiting for?";
		} else if(dialogueSet == 2) {
			dialogue[0] = "Ahhh. \nThank you so much for opening \nthat gate.";
			dialogue[1] = "I think I would have sunk into that \npond back there if I stayed \na second longer.";
			dialogue[2] = "It's good to be back in my old house.";
			dialogue[3] = "Oh? You're still here?";
			dialogue[4] = "Why don't you do me a favor and \ndeal with those ugly creatures up \nnorth past the other gate for me";
			dialogue[5] = "Ah yes I forgot to give you the key \nto the gate. \nHere you go!";
			dialogue[6] = "Have you dealt with them yet?";
			dialogue[7] = "Hurry please.";
			dialogue[8] = "What are you waiting for?";
		} else if(dialogueSet == 3) {
			dialogue[0] = "Thank You so Much!!";
			dialogue[1] = "Now that they're gone I can \ngive you the key to the last gate";
			dialogue[2] = "Thats where you'll find treasure!!\n( or so they think >:) )";
			dialogue[3] = "\"Laughs Maniacally\"";
		}
	}
	public void speak() {
		if(dialogueSet == 1 || dialogueSet == 3) {
			if(dialogue[dialogueIndex] == null) {
				dialogueIndex = 3;
			}
		
			gp.UI.currentDialogue = dialogue[dialogueIndex];
			dialogueIndex++;
		} else if(dialogueSet == 2) {
			if(dialogue[dialogueIndex] == null) {
				dialogueIndex = 6;
			}
		
			gp.UI.currentDialogue = dialogue[dialogueIndex];
			dialogueIndex++;
		}
		
	}
}
