package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.imageio.ImageIO;


import main.GamePanel;

public class TileManager {
//					 TileNum ↓       ↓ Speed Multiplier
	private static HashMap<Integer, Double> tileSpeed = new HashMap<Integer, Double>();
	public synchronized static HashMap<Integer, Double> getSpeed(){
		return tileSpeed;
	}
	
	GamePanel gp;
	public Tile[] tile; //Tile array
	public int mapTileNum[][]; 
	public static int tileSet = 0;

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[1000]; //Tile Index Cap
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/World1Editor.txt");
	}

	public void getTileImage() {

		try {
			for (int i = 0; i < 100; i++) { //Invalidates first 99 tiles as void tiles
				tile[i] = new Tile();
				tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Void.png"));
				tile[i].collision = true;
			}
			if (tileSet == 0) {
				tile[100] = new Tile(); //100
				tile[100].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Void.png"));
	
				tile[101] = new Tile(); //101
				tile[101].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));
				tileSpeed.put(101, 0.9);
	
				tile[102] = new Tile(); //102
				tile[102].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path1.png"));
				tileSpeed.put(102, 1.2);
	
				tile[103] = new Tile(); //103
				tile[103].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path2.png"));
				tileSpeed.put(103, 1.2);
	
				tile[104] = new Tile(); //104
				tile[104].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
				tile[104].collision = true;
	
				tile[105] = new Tile(); //105
				tile[105].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));
				tileSpeed.put(105, 0.8);
	
				tile[106] = new Tile(); //106 -- Horizontal Bridge
				tile[106].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BridgeLR.png"));
				tileSpeed.put(106, 1.1);
	
				tile[107] = new Tile(); //107 -- Vertical Bridge
				tile[107].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BridgeUD.png"));
				tileSpeed.put(107, 1.1);
	
				tile[108] = new Tile(); //108 -- Tree Bottom
				tile[108].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree1.png"));
				tile[108].collision = true;
	
				tile[109] = new Tile(); //109 -- Real Tree Top
				tile[109].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree2.png"));
				tile[109].collision = true;
				tile[109].topLayer = true;
	
				tile[110] = new Tile(); //110 -- Linked Tree Top
				tile[110].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree3.png"));
				tile[110].collision = true; 
				tile[110].topLayer = true;
	
				tile[111] = new Tile(); //111 -- False tree top
				tile[111].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree2.png"));
				tile[111].topLayer = true;
				
				tile[112] = new Tile(); //112 -- Grass->Sand Bottom
				tile[112].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-B.png"));
				
				tile[113] = new Tile(); //113 -- Grass->Sand BottomLeft
				tile[113].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-BL-L.png"));
				
				tile[114] = new Tile(); //114 -- Grass->Sand BottomRight
				tile[114].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-BR-L.png"));
				
				tile[115] = new Tile(); //115 -- Grass->Sand Left
				tile[115].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-L.png"));
				
				tile[116] = new Tile(); //116 -- Grass->Sand TopLeft
				tile[116].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-TL-L.png"));
				
				tile[117] = new Tile(); //117 -- Grass->Sand Top
				tile[117].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-T.png"));
				
				tile[118] = new Tile(); //118 -- Grass->Sand TopRight
				tile[118].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-TR-L.png"));
				
				tile[119] = new Tile(); //119 -- Grass->Sand Right
				tile[119].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-R.png"));
				
				tile[120] = new Tile(); //119 -- Grass->Sand BottomLeftCorner
				tile[120].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-BL-S.png"));
				
				tile[121] = new Tile(); //120 -- Grass->Sand BottomRightCorner
				tile[121].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-BR-S.png"));
				
				tile[122] = new Tile(); //122 -- Grass->Sand TopLeftCorner
				tile[122].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-TL-S.png"));
				
				tile[123] = new Tile(); //123 -- Grass->Sand TopRightCorner
				tile[123].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GS-TR-S.png"));
	
				for(int c = 112; c < 123; c++) {
					tileSpeed.put(c, 0.85);
				}
				
				tile[124] = new Tile();
				tile[124].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-TR-L.png"));
				
				tile[125] = new Tile();
				tile[125].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-R.png"));

				tile[126] = new Tile();
				tile[126].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-BR-L.png"));

				tile[127] = new Tile();
				tile[127].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-B.png"));

				tile[128] = new Tile();
				tile[128].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-BL-L.png"));

				tile[129] = new Tile();
				tile[129].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-L.png"));

				tile[130] = new Tile();
				tile[130].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-TL-L.png"));

				tile[131] = new Tile();
				tile[131].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-T.png"));

				tile[132] = new Tile();
				tile[132].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-TL-S.png"));

				tile[133] = new Tile();
				tile[133].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-BL-S.png"));

				tile[134] = new Tile();
				tile[134].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-TR-S.png"));

				tile[135] = new Tile();
				tile[135].image = ImageIO.read(getClass().getResourceAsStream("/tiles/GW-BR-S.png"));
				for(int c = 124; c < 136; c++) {
					tile[c].collision = true;
				}
				//Invalidates the rest of the tiles as void tiles
				for (int i = 136; i < 1000; i++) {
					tile[i] = new Tile();
					tile[i].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Void.png"));
					tile[i].collision = true;
				}
				// SCALES EACH TIME IMAGE TO THE TILE SIZE
				for(Tile a : tile) {
					BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, a.image.getType());
					Graphics2D g2 = scaledImage.createGraphics();
					g2.drawImage(a.image, 0, 0, gp.tileSize, gp.tileSize, null);
					a.image = scaledImage;
					g2.dispose();
				}
			} else if (tileSet == 1) {
				for(int c = 100; c<124; c++) {
					tile[c] = new Tile();
					tile[c].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Void.png"));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setUp(int index, String location, double speedModifier, boolean collision, boolean topLayer) {
		index+=100;
		tile[index] = new Tile();
		try {
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileSpeed.put(index, speedModifier);
		tile[index].collision = collision;
		tile[index].topLayer = topLayer;
	}
	public void setUp(int index, String location, double speedModifier, boolean collision) {
		index+=100;
		tile[index] = new Tile();
		try {
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileSpeed.put(index, speedModifier);
		tile[index].collision = collision;
	}
	public void setUp(int index, String location, double speedModifier) {
		index+=100;
		tile[index] = new Tile();
		try {
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileSpeed.put(index, speedModifier);
	}
	public void setUp(int index, String location) {
		index+=100;
		tile[index] = new Tile();
		try {
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String a) {

		try {
			InputStream is = getClass().getResourceAsStream(a);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			int col = 0;
			int row = 0;

			while (col < gp.maxWorldCol && row < gp.maxWorldRow) {

				String line = br.readLine();

				while (col < gp.maxWorldCol) {

					String numbers[] = line.split(" ");

					int num = Integer.parseInt(numbers[col]);

					mapTileNum[col][row] = num;
					col++;
				}
				if (col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			br.close();
		} catch (Exception e) {

		}

	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + (gp.tileSize) > gp.player.worldX - gp.player.screenX
					&& worldX - (gp.tileSize) < gp.player.worldX + gp.player.screenX
					&& worldY + (gp.tileSize) > gp.player.worldY - gp.player.screenY
					&& worldY - (gp.tileSize) < gp.player.worldY + gp.player.screenY) {
				if(tile[tileNum].topLayer || tileNum == 109) {
					g2.drawImage(tile[101].image, screenX, screenY, null);
				}
				g2.drawImage(tile[tileNum].image, screenX, screenY, null);
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}

	public void drawTop(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

			int tileNum = mapTileNum[worldCol][worldRow];

			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;

			if (worldX + (gp.tileSize) > gp.player.worldX - gp.player.screenX
					&& worldX - (gp.tileSize) < gp.player.worldX + gp.player.screenX
					&& worldY + (gp.tileSize) > gp.player.worldY - gp.player.screenY
					&& worldY - (gp.tileSize) < gp.player.worldY + gp.player.screenY) {
			// CHECKS IF THE IMAGE SHOULD BE DRAW ON THE TOP LAYER
				if (tile[tileNum].topLayer) { 
					g2.drawImage(tile[tileNum].image, screenX, screenY, null);
				}
			}

			worldCol++;

			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;
			}
		}

	}
}
