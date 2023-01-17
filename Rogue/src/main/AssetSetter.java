package main;

import entity.NPC_Enemy;
import entity.NPC_Sir_Jefferies;
import item.Chest;
import item.Door;
import item.Gate;
import item.Key;

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
		setupItem("Gate", 132, 107, true);

	}

	public void setNPC() {

		gp.npc[0] = new NPC_Sir_Jefferies(gp);
		gp.npc[0].worldX = gp.tileSize * 108;
		gp.npc[0].worldY = gp.tileSize * 120;
		
		gp.npc[1] = new NPC_Enemy(gp);
		gp.npc[1].worldX = gp.tileSize * 123;
		gp.npc[1].worldY = gp.tileSize * 91;
		
		gp.npc[2] = new NPC_Enemy(gp);
		gp.npc[2].worldX = gp.tileSize * 123;
		gp.npc[2].worldY = gp.tileSize * 86;
	}

	public void setupItem(String itemType, int x, int y, boolean collision) {
		switch (itemType) {
		case "Key":
			gp.obj[itemCount] = new Key();
			break;
		case "Door":
			gp.obj[itemCount] = new Door();
			break;
		case "Chest":
			gp.obj[itemCount] = new Chest();
			break;
		case "Gate":
			gp.obj[itemCount] = new Gate();
			break;
		}

		gp.obj[itemCount].worldX = worldX(x * gp.tileSize);
		gp.obj[itemCount].worldY = worldY(y * gp.tileSize);
		gp.obj[itemCount].collision = collision;
		itemCount++;
	}
	public void setupItem(String itemType, int x, int y) {
		switch (itemType) {
		case "Key":
			gp.obj[itemCount] = new Key();
			break;
		case "Door":
			gp.obj[itemCount] = new Door();
			break;
		case "Chest":
			gp.obj[itemCount] = new Chest();
			break;
		}
		gp.obj[itemCount].worldX = worldX(x * gp.tileSize);
		gp.obj[itemCount].worldY = worldY(y * gp.tileSize);
		itemCount++;
	}
}
