package main;

import entity.NPC_Enemy;
import entity.Entity;
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
	Attack a;
	Entity e;
	int itemCount = 0;


	public AssetSetter(GamePanel gp, KeyHandler keyH, CollisionDetecter cd, Player p, Attack a, Entity e) {
		this.gp = gp;
		this.keyH = keyH;
		this.cd = cd;
		this.p = p;
		this.a = a;
		this.e = e;
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
	public boolean x = true;
	public boolean z = false;
	public boolean num0() {
		return x;
	}
	public void num1(boolean q) {
		x = q;
	}
	public boolean allow() {
		return z;
	}
	public void allow(boolean q) {
		z = q;
	}
	public void setAttack() {
		
		//System.out.println(cd.tilePositionUpon(p).get(1));
	
		int duration = 31;
		int cooldown = 62;
		
//		if (num0()) {
//		gp.atk[0] = new Attack(gp);
//		num1(false);
//		}
		
		int delay = gp.delay();
		//System.out.println(delay);
		//System.out.println(delay);
	//	System.out.println(delay + " main");
//		if (duration < delay) {
//		allow = 1;
//		}
		if (delay >= cooldown || allow()) {

		if (keyH.spacePressed || allow()) {
			
			if (delay>=cooldown) {
			gp.delay(0);
			delay = gp.delay();
			allow(true);
			}
			//delay = gp.delay();
			
			//System.out.println(e.collisionOn);
//			System.out.println(e.direction);
//			if (delay > 31) {
//				allow = 100;
//			}
//				System.out.println(delay + " sub");
			gp.atk[0] = new Attack(gp);
			int Col = (int) cd.tilePositionUpon(p).get(0);
			int Row = (int) cd.tilePositionUpon(p).get(1);
			//System.out.println(Col);
			gp.atk[0] = new Attack(gp);
			a.hitBox.x = e.hx;
			a.hitBox.y = e.hy;
			e.direction = "atk1";
			gp.cDetect.checkEntity(a, gp.npc, 1.0);
			boolean hit = cd.collision();
			
			
			if (p.direction.equals("up")) {
				e.hx = Col;
				e.hy = (int)(Row - gp.tileSize *1.3);
				gp.atk[0].worldX = e.hx ;
				gp.atk[0].worldY = e.hy;
				
				if (hit) {
					System.out.println("Successful Attack!");
				}
			}
			if (p.direction.equals("down")) {
				e.hx = Col;
				e.hy = (int)(Row + gp.tileSize);
				gp.atk[0].worldX = e.hx ;
				gp.atk[0].worldY = e.hy;
				if (hit) {
					System.out.println("Successful Attack!");
				}
			}
			if (p.direction.equals("left")) {
				e.hx = (int)(Col - gp.tileSize * 1.3);
				e.hy = Row;
				gp.atk[0].worldX = e.hx;
				gp.atk[0].worldY = e.hy ;
				if (hit) {
					System.out.println("Successful Attack!");
				}
			}
		
			if (p.direction.equals("right")) {//gp.tileSize
				e.hx = Col + gp.tileSize;
				e.hy = Row;
				gp.atk[0].worldX = e.hx ;
				gp.atk[0].worldY = e.hy;
				if (hit) {
					System.out.println("Successful Attack!");
				}
			}
			
			
			
			
			if (delay >= duration) {
				allow(false);
				gp.atk[0] = new Attack(gp);
			}
		
				
			
			}
		
		}
		
		
		
		//}
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
