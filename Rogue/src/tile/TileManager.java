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

	public synchronized static HashMap<Integer, Double> getSpeed() {
		return tileSpeed;
	}

	GamePanel gp;
	public Tile[] tile; // Tile array
	public int mapTileNum[][];
	public static int tileSet = 0;

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[1000]; // Tile Index Cap
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		loadMap("/maps/World1Editor.txt");
	}

	public void getTileImage() {

		setup(0, "Void", true);

		setup(1, "Grass", 0.9); // Grass

		setup(2, "Path1", 1.2); // Path1
		setup(3, "Path2", 1.2); // Path2

		setup(4, "Water", true); // Water

		setup(5, "Sand", 0.8); // Sand

		setup(6, "BridgeLR", 1.1); // Horizontal Bridge
		setup(7, "BridgeUD", 1.1); // Vertical Bridge

		setup(8, "Tree1", true); // Tree Bottom

		setup(9, "Tree2", true); // Real Tree Top

		setup(10, "Tree3", true, true); // Linked Tree Top

		setup(11, "Tree2", 0.9, false, true); // False Tree Top

		String[] TransitionTextureKey = { "T", "R", "L", "B", "TR-L", "TR-S", "TL-L", "TL-S", "BR-L", "BR-S", "BL-L",
				"BL-S" };
		int counter = 12;
		for (String i : TransitionTextureKey) {
			// Grass -> Sand Transition
			setup(counter, "GS-" + i, 0.85);
			// Grass -> Water Transition
			setup(counter + 12, "GW-" + i, true);
			counter++;
		}
		// SCALES EACH IMAGE TO THE PROPER SIZE SIZE
		for (int i = 100; i < 136; i++) {
			BufferedImage scaledImage = new BufferedImage(gp.tileSize, gp.tileSize, tile[i].image.getType());
			Graphics2D g2 = scaledImage.createGraphics();
			g2.drawImage(tile[i].image, 0, 0, gp.tileSize, gp.tileSize, null);
			tile[i].image = scaledImage;
			g2.dispose();
		}
	}

	// YAY OVERIDING CODE
	public void setup(int index, String location, double speedModifier, boolean collision, boolean topLayer) {
		index += 100;
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileSpeed.put(index, speedModifier);
		tile[index].collision = collision;
		tile[index].topLayer = topLayer;
	}

	public void setup(int index, String location, boolean collision, boolean topLayer) {
		index += 100;
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tile[index].collision = collision;
		tile[index].topLayer = topLayer;
	}

	public void setup(int index, String location, boolean collision) {
		index += 100;
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tile[index].collision = collision;
	}

	public void setup(int index, String location, double speedModifier) {
		index += 100;
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + location + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		tileSpeed.put(index, speedModifier);
	}

	public void setup(int index, String location) {
		index += 100;
		try {
			tile[index] = new Tile();
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
				if (tile[tileNum].topLayer || tileNum == 109) {
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
