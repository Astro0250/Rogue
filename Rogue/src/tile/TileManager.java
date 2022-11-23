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
	public Tile[] tile;
	public int mapTileNum[][];

	public TileManager(GamePanel gp) {

		this.gp = gp;

		tile = new Tile[9];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

		getTileImage();
		// MAP TEXT FILE IMPORT
		loadMap("/maps/map2.txt");
	}

	public void getTileImage() {

		try {

			tile[0] = new Tile();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Grass.png"));

			tile[1] = new Tile();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Path.png"));

			tile[2] = new Tile();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Dirt.png"));

			tile[3] = new Tile();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Void.png"));
			tile[3].collision = true;

			tile[4] = new Tile();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Water.png"));
			tile[4].collision = true;

			tile[5] = new Tile();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sand.png"));
			
			tile[6] = new Tile();
			tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BridgeLR.png"));
			
			tile[7] = new Tile();
			tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/BridgeUD.png"));
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadMap(String a) {

		try {
			// TEXT FILE ---> USABLE MAP INFO
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
		}
		catch (Exception e) {}
	}

	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			// VARIABLE DECLARATION
			int tileNum = mapTileNum[worldCol][worldRow];
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			// SCREEN PARAMETER CHECK
			if(worldX + (gp.tileSize)> gp.player.worldX - gp.player.screenX && 
			   worldX - (gp.tileSize) < gp.player.worldX + gp.player.screenX && 
			   worldY + (gp.tileSize) > gp.player.worldY - gp.player.screenY && 
			   worldY - (gp.tileSize) < gp.player.worldY + gp.player.screenY) {
				// DRAWS TILES JUST OUTSIDE USER VIEW
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}

			worldCol++;
			// RESETS COL AND START NEW COL
			if (worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}

	}
}
