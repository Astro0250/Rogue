package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

	GamePanel gp;
	public Tile[] tile; //Tile array
	public int mapTileNum[][]; 

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[999]; //Tile Index Cap
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
			
			tile[100] = new Tile(); //100
			tile[100].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Void.png"));
			tile[100].collision = true;

			tile[101] = new Tile(); //101
			tile[101].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));
			

			tile[102] = new Tile(); //102
			tile[102].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path1.png"));

			tile[103] = new Tile(); //103
			tile[103].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path2.png"));

			tile[104] = new Tile(); //104
			tile[104].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
			tile[104].collision = true;

			tile[105] = new Tile(); //105
			tile[105].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));

			tile[106] = new Tile(); //106 -- Horizontal Bridge
			tile[106].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BridgeLR.png"));

			tile[107] = new Tile(); //107 -- Vertical Bridge
			tile[107].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BridgeUD.png"));

			tile[108] = new Tile(); //108
			tile[108].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree1.png"));
			tile[108].collision = true;

			tile[109] = new Tile(); //109 -- Real tree top
			tile[109].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree2.png"));
			tile[109].collision = true;
			tile[109].topLayer = true;

			tile[110] = new Tile(); //110
			tile[110].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree3.png"));
			tile[110].collision = true; 
			tile[110].topLayer = true;

			tile[111] = new Tile(); //111 -- False tree top
			tile[111].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Tree2.png"));
			tile[111].topLayer = true;
			
			tile[112] = new Tile(); //112
			tile[112].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Bottom.png"));
			
			tile[113] = new Tile(); //113
			tile[113].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Bottom-Left.png"));
			
			tile[114] = new Tile(); //114
			tile[114].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Bottom-Right.png"));
			
			tile[115] = new Tile(); //115
			tile[115].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Left.png"));
			
			tile[116] = new Tile(); //116
			tile[116].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Top-Left.png"));
			
			tile[117] = new Tile(); //117
			tile[117].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Top.png"));
			
			tile[118] = new Tile(); //118
			tile[118].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Top-Right.png"));
			
			tile[119] = new Tile(); //119
			tile[119].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass-Sand-Right.png"));

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
					g2.drawImage(tile[101].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
					g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
