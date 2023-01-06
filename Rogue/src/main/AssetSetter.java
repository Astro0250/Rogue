package main;

import entity.NPC_Enemy;
import entity.Attack;
import entity.Player;
import item.Chest;
import item.Door;
import item.Key;

public class AssetSetter {
	GamePanel gp;
	KeyHandler keyH;
	CollisionDetecter cd;
	Player p;
	int itemCount = 0;


	public AssetSetter(GamePanel gp, KeyHandler keyH, CollisionDetecter cd, Player p) {
		this.gp = gp;
		this.keyH = keyH;
		this.cd = cd;
		this.p = p;
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
		
		gp.npc[0] = new NPC_Enemy(gp);
		gp.npc[0].worldX = gp.tileSize*123;
		gp.npc[0].worldY = gp.tileSize*91;
	}
	public void setAttack() {
		//System.out.println(cd.tilePositionUpon(p).get(1));
		gp.atk[0] = new Attack(gp);

		
		if (keyH.spacePressed) {
	
			
			int Col = (int) cd.tilePositionUpon(p).get(0);
			int Row = (int) cd.tilePositionUpon(p).get(1);

			if (p.direction.equals("up")) {
			gp.atk[0].worldX = Col ;
			gp.atk[0].worldY = (int)(Row - gp.tileSize *1.3);
			}
			if (p.direction.equals("down")) {
				gp.atk[0].worldX = Col ;
				gp.atk[0].worldY = Row + gp.tileSize;
			}
			if (p.direction.equals("left")) {
				gp.atk[0].worldX = (int)(Col - gp.tileSize * 1.3);
				gp.atk[0].worldY = Row ;
			}
		
			if (p.direction.equals("right")) {//gp.tileSize
				gp.atk[0].worldX = Col + gp.tileSize ;
				gp.atk[0].worldY = Row ;
			}
		}
	}
	public void setupItem(String itemType, int x, int y, boolean collision) {
		switch(itemType) {
			case "Key": gp.obj[itemCount] = new Key(); break;
			case "Door": gp.obj[itemCount] = new Door(); break;
			case "Chest": gp.obj[itemCount] = new Chest(); break;
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
		}
		gp.obj[itemCount].worldX = worldX(x*gp.tileSize);
		gp.obj[itemCount].worldY = worldY(y*gp.tileSize);
		itemCount++;
	}
}
