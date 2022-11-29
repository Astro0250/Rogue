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
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
	public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
	
	// WORLD SETTINGS
	public final int maxWorldCol = 62;
	public final int maxWorldRow = 53;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	// FPS
	int FPS = 60;

	// INSTANTIATION
	TileManager tileM = new TileManager(this);
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	public CollisionDetecter cDetect = new CollisionDetecter(this);
	public AssetSetter aSetter = new AssetSetter(this);
	public Player player = new Player(this, keyH);
	public Item obj[] = new Item[10];
	

	public GamePanel() { // GAME PANEL INSTANTIATION

		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(new Color(76,103,133));
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void setupItems() {
		
		aSetter.setObject();
	}

	public void startGameThread() {

		gameThread = new Thread(this);
		gameThread.start();
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
		
		g2.dispose();
	}

}
