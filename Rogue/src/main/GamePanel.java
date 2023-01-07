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
	public int tileSize() {
		return tileSize;
	}
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
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
		// SYSTEM
		TileManager tileM = new TileManager(this, 1);
		KeyHandler keyH = new KeyHandler(this);
		Sound music = new Sound();
		Sound soundEffect = new Sound();
		Attack a = new Attack(this);
		Entity e = new Entity(this);
		public CollisionDetecter cDetect = new CollisionDetecter(this);
		
		public UserInterface UI = new UserInterface(this);
		Thread gameThread;
		
		// ENTITY & ITEM
		public Player player = new Player(this, keyH);
		public AssetSetter aSetter = new AssetSetter(this, keyH, cDetect, player, a, e);
		public Item obj[] = new Item[20];
		public Entity npc[] = new Entity[10];
		public Entity atk[] = new Entity[1];
		
		// GAME STATE
		public int gameState;
		public int playState = 1;
		public int pauseState = 2;
	

	public GamePanel() { // GAME PANEL INSTANTIATION

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(76,103,133));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		aSetter.setNPC();
		aSetter.setAttack();
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

	public void update() {
		//Clear it maybe it will work then, or at least see the errors
//		if (keyH.shiftPressed == true) {
//			TileManager tileM = new TileManager(this, 2);
//		}
		if (gameState == playState) {
			// Player
			player.update();
			aSetter.setAttack();
			//NPC
			for (int i = 0; i < npc.length; i++) {
				if (npc[i] != null) {
					npc[i].update();
				}
			}
			//atk
			for (int i = 0; i < atk.length; i++) {
				if (atk[i] != null) {
					atk[i].update();
				}
			}
			//cooldown
			delay++;
		} else if (gameState == pauseState) {
			// nothing for now
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
	//	while (delay <= 30) {
		//System.out.println(atk[0].worldX);
		for(int i = 0; i < atk.length; i++) {
			if(atk[i] != null ) {
				atk[i].draw(g2);
			}
		}
	//	}
		
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
