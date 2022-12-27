package main;

import javax.swing.*;

import entity.Player;
import item.AssetSetter;
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
		// SYSTEM
		TileManager tileM = new TileManager(this);
		KeyHandler keyH = new KeyHandler();
		Sound music = new Sound();
		Sound soundEffect = new Sound();
		public CollisionDetecter cDetect = new CollisionDetecter(this);
		public AssetSetter aSetter = new AssetSetter(this);
		public UserInterface UI = new UserInterface(this);
		Thread gameThread;
		
		// ENTITY & ITEM
		public Player player = new Player(this, keyH);
		public Item obj[] = new Item[20];
	

	public GamePanel() { // GAME PANEL INSTANTIATION

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(76,103,133));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupGame() {
		
		aSetter.setObject();
		
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
		player.update();
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
		for(int i = 0; i<obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
				
			}
		}
		
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
