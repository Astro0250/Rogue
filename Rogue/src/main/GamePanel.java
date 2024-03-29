package main;

import javax.swing.*;

import entity.Entity;
import entity.Player;
import entity.Attack;
import item.Item;
import tile.TileManager;


import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

	// SCREEN SETTINGS
	final int originalTileSize = 16; // 16x16 tile
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48 tile
	// FULLSCREEN SETTINGS COL - 32 ROW - 20
	// BASE SETTINGS COL - 16 ROW - 12
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 200;
	public final int maxWorldRow = 200;
	
	// FPS
	int FPS = 60;

	// INSTANTIATION
		private int delay;
		public int delay() {
			return delay;
		}
		public void delay(int d) {
			delay = d;
		}
		private int delayPlayer;
		public int delayPlayer() {
			return delay;
		}
		public void delayPlayer(int d) {
			delay = d;
		}
		// SYSTEM
		TileManager tileM = new TileManager(this, 1);
		public KeyHandler keyH = new KeyHandler(this);
		Sound music = new Sound();
		Sound soundEffect = new Sound();
		public CollisionDetecter cDetect = new CollisionDetecter(this);
		
		public UserInterface UI = new UserInterface(this);
		Thread gameThread;
		
		// ENTITY & ITEM
		public Player player = new Player(this, keyH);
		public Attack attack = new Attack(this);
		public AssetSetter aSetter = new AssetSetter(this);
		public Item obj[] = new Item[20];
		public Entity npc[] = new Entity[30];
		public Attack atk = new Attack(this);
		
		// GAME STATE
		public int gameState;
		public int playState = 1;
		public int pauseState = 2;
		public int deathState = 3;
		public int dialogueState = 4;
		public int classState = 5;
	
	// GAME PANEL INSTANTIATION
	public GamePanel() { 
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(76,103,133));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		aSetter.setObject();
		aSetter.setNPC();
		attack.setAttack();
		gameState = playState;
		loopMusic(1);
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
	}
	public void stopGameThread() {
		
		gameThread.stop();
	}

	@Override
	public void run() { // DELTA GAME LOOP

		double drawInterval = 1000000000 / (FPS);
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;

		while (gameThread != null) {

			currentTime = System.nanoTime();

			delta += (currentTime - lastTime) / drawInterval;
			lastTime = currentTime;
			

			if (delta >= 1) {
				update();
				repaint();
				delta--;
			}

		}

	}
	//Makes one noise
	private int unecessary = 0;
	public int unecessary() {
		return unecessary;
	}
	public void unecessary(int a) {
		unecessary = a;
	}
	public void update() {
		//Clear it maybe it will work then, or at least see the errors
//		if (keyH.shiftPressed == true) {
//			TileManager tileM = new TileManager(this, 2);
//		}
		
		if (player.health <= 0) {
			gameState = deathState;
		}
		if (gameState == playState) {
			// Player
			player.update();
			attack.setAttack();
			//NPC
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			//atk
			atk.setAttack();;
			//cooldown
			delay++;
			delayPlayer++;
		} else if (gameState == pauseState) {
			// nothing for now
			//System.out.println(player.worldX);
			//System.out.println(player.worldY);

		} else if (gameState == deathState) {
			stopMusic();
			player.spriteNum = 5;
			playSoundEffect(3);
//			if (unecessary() == 0) {
//				playSoundEffect(3);
//				//System.out.println(unecessary);
//			}
//			unecessary(5);
		}
	}
	

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		// DEBUG
		long drawStart = 0;
		if(keyH.checkDrawTime) {
			drawStart = System.nanoTime();
			tileM.tile[104].collision = false;
			for(int i = 124; i < 136; i++) {
				tileM.tile[i].collision = false;
			}
		}
		
		// TILE

		tileM.draw(g2);
		
		// ITEM
		for(int i = 0; i < obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
				
			}
		}
		// NPC
		for(int i = 0; i < npc.length; i++) {
			if(npc[i] != null) {
				npc[i].draw(g2);
			}
		}
		// ATK
		atk.draw(g2);	
		
		// PLAYER
		player.draw(g2);
		
		// TOP LAYER TILES
		tileM.drawTop(g2);


		
		// USER INTERFACE
		UI.draw(g2);
		
		// DEBUG
		if(keyH.checkDrawTime) {
			player.speed = 15;
			long drawEnd = System.nanoTime();
			long passed = (drawEnd - drawStart);
			g2.setColor(Color.white);
			g2.setFont(new Font("Ariel", Font.PLAIN, 20));
			g2.drawString("Draw Time: " + passed, 10, 400);
			System.out.println("Draw Time: " + passed);
		} else {
			player.speed = 8;
			tileM.tile[104].collision = true;
			for(int i = 124; i < 136; i++) {
				tileM.tile[i].collision = true;
			}
		}
		if(gameState == classState) {
			UI.drawClassifiedDoc(g2);
		}
		g2.dispose();
	}
	public void loopMusic(int i) {
		
		music.setFile(i);
		music.play();
		music.loop();
	}
	public void stopMusic() {
		
		music.stop();
	}
	public void playSoundEffect(int i) {
		
		soundEffect.setFile(i);
		soundEffect.play();
	}

}
