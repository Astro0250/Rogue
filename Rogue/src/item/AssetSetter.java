package item;

import entity.NPC_Max;
import main.GamePanel;

public class AssetSetter {
	GamePanel gp;
	int itemCount = 0;

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
		
	}
	public void setNPC() {
		
		gp.npc[0] = new NPC_Max(gp);
		gp.npc[0].worldX = gp.tileSize*109;
		gp.npc[0].worldY = gp.tileSize*120;
	}
	public void setupItem(String itemType, int x, int y, boolean collision) {
		switch(itemType) {
			case "Key": gp.obj[itemCount] = new Key(); break;
			case "Door": gp.obj[itemCount] = new Door(); break;
			case "Chest": gp.obj[itemCount] = new Chest(); break;
			case "SpeedPotion": gp.obj[itemCount] = new SpeedPotion(); break;
		}
		gp.obj[itemCount].worldX = worldX(x*gp.tileSize);
		gp.obj[itemCount].worldY = worldY(y*gp.tileSize);
		gp.obj[itemCount].collision = collision;
		itemCount++;
	}
	public void setupItem(String itemType, int x, int y) {
		switch(itemType) {
			case "Key": gp.obj[itemCount] = new Key(); break;
			case "Door": gp.obj[itemCount] = new Door(); break;
			case "Chest": gp.obj[itemCount] = new Chest(); break;
			case "SpeedPotion": gp.obj[itemCount] = new SpeedPotion(); break;
			case "EnemyTemp": gp.obj[itemCount] = new EnemyTemp(); break;
		}
		gp.obj[itemCount].worldX = worldX(x*gp.tileSize);
		gp.obj[itemCount].worldY = worldY(y*gp.tileSize);
		itemCount++;
	}
}
