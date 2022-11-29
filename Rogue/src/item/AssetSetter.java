package item;

import main.GamePanel;

public class AssetSetter {
	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		
		gp.obj[0] = new Chest();
		gp.obj[0].worldX = 31 * gp.tileSize;
		gp.obj[0].worldY = 38 * gp.tileSize;
		
		gp.obj[1] = new Key();
		gp.obj[1].worldX = 55 * gp.tileSize;
		gp.obj[1].worldY = 38 * gp.tileSize;
		
		gp.obj[2] = new Door();
		gp.obj[2].worldX = 37 * gp.tileSize;
		gp.obj[2].worldY = 38 * gp.tileSize;
		
		gp.obj[3] = new Key();
		gp.obj[3].worldX = 39 * gp.tileSize;
		gp.obj[3].worldY = 17 * gp.tileSize;
		
	}
}
