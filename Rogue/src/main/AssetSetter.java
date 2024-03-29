package main;

import entity.NPC_Enemy;
import entity.NPC_Sir_Jefferies;
import item.*;

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
		setupItem(0, "GateHor", 131, 107, true);
		setupItem(1, "GateVertLR", 50, 154, true);
		setupItem(2, "Chest", 151, 91, true);
		setupItem(3, "GateVertLR", 137, 90, true);


	}

	public void setNPC() {

		gp.npc[0] = new NPC_Sir_Jefferies(gp, 2);
		gp.npc[0].worldX = gp.tileSize * 108;
		gp.npc[0].worldY = gp.tileSize * 120;
		
		gp.npc[1] = new NPC_Enemy(gp);
		gp.npc[1].worldX = gp.tileSize * 123;
		gp.npc[1].worldY = gp.tileSize * 91;
		
		gp.npc[2] = new NPC_Enemy(gp);
		gp.npc[2].worldX = gp.tileSize * 123;
		gp.npc[2].worldY = gp.tileSize * 86;
		
		gp.npc[3] = new NPC_Sir_Jefferies(gp, 1);
		gp.npc[3].worldX = (gp.tileSize * 34) - gp.tileSize/2;
		gp.npc[3].worldY = gp.tileSize * 162;
		
		gp.npc[4] = new NPC_Enemy(gp);
		gp.npc[4].worldX = gp.tileSize * 150;
		gp.npc[4].worldY = gp.tileSize * 91;
		
		int crafty = 152;
		int miney = 92;
		double undertale;
		for(int i = 5; i < 29; i++) {
			gp.npc[i] = new NPC_Enemy(gp);
			gp.npc[i].worldX = gp.tileSize * crafty;
			gp.npc[i].worldY = gp.tileSize * miney;
			undertale = Math.random()*10+1;
			if (undertale < 3) {
				crafty += 1;
				miney -= 1;
			}else if(undertale < 5){
				crafty -= 1;
				miney -= 1;
			}else if(undertale < 7) {
				crafty -= 1;
				miney += 1;
			}else{
				crafty += 1;
				miney += 1;
			}
		}
	}

	public void setupItem(int itemCount, String itemType, int x, int y, boolean collision) {
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
		case "GateHor":
			gp.obj[itemCount] = new GateHor();
			break;
		case "GateVertLR":
			gp.obj[itemCount] = new GateVertLR();
			break;
		case "GateVertRL":
			gp.obj[itemCount] = new GateVertRL();
			break;
		}
		

		gp.obj[itemCount].worldX = worldX(x * gp.tileSize);
		gp.obj[itemCount].worldY = worldY(y * gp.tileSize);
		gp.obj[itemCount].collision = collision;
	}
	public void setupItem(int itemCount, String itemType, int x, int y) {
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
	}
}
