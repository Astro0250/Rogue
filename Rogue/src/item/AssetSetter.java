package item;

import main.GamePanel;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public int worldX(int xcoord) {
		int worldX = 0;
		return worldX = xcoord - gp.tileSize;
	}
	public int worldY(int ycoord) {
		int worldY = 0;
		return worldY = ycoord - gp.tileSize;
	}
	public void setObject() {
		
		// INSTANTIATES AND PLACES ITEMS ON THE MAP
		
		gp.obj[0] = new Chest();
		gp.obj[0].worldX = worldX(32 * gp.tileSize);
		gp.obj[0].worldY = worldY(38 * gp.tileSize);
		
		gp.obj[1] = new Key();
		gp.obj[1].worldX = worldX(56 * gp.tileSize);
		gp.obj[1].worldY = worldY(38 * gp.tileSize);
		
		gp.obj[2] = new Door();
		gp.obj[2].worldX = worldX(37 * gp.tileSize);
		gp.obj[2].worldY = worldY(38 * gp.tileSize);
	
		gp.obj[3] = new Key();
		gp.obj[3].worldX = worldX(40 * gp.tileSize);
		gp.obj[3].worldY = worldY(16 * gp.tileSize);
		
		gp.obj[4] = new SpeedPotion();
		gp.obj[4].worldX = worldX(33 * gp.tileSize);
		gp.obj[4].worldY = worldY(25 * gp.tileSize);
		
		gp.obj[5] = new Door();
		gp.obj[5].worldX = worldX(39 * gp.tileSize);
		gp.obj[5].worldY = worldY(38 * gp.tileSize);
		
	}
}
