package main;

import entity.Entity;
import java.util.*;
import java.util.function.Supplier;

public class CollisionDetecter {

	GamePanel gp;

	public CollisionDetecter(GamePanel gp) {
		this.gp = gp;
	}

	// This will probably be important when certain tiles give you buffs
	// or hurt you or something, so here have a comment
	public int tileStoodUpon(Entity entity) {
		int entityMiddleWorldX = entity.worldX + entity.hitBox.x + (entity.hitBox.width / 2);
		int entityMiddleWorldY = entity.worldY + entity.hitBox.y + (entity.hitBox.height / 2);

		int entityMiddleCol = entityMiddleWorldX / gp.tileSize;
		int entityMiddleRow = entityMiddleWorldY / gp.tileSize;

		int tileNumOn = gp.tileM.mapTileNum[entityMiddleCol][entityMiddleRow];
		return tileNumOn;
	}
	public List tilePositionUpon(Entity entity) {
	
		int entityMiddleWorldX = entity.worldX + entity.hitBox.x + (entity.hitBox.width / 2);
		int entityMiddleWorldY = entity.worldY + entity.hitBox.y + (entity.hitBox.height / 2);

		//int entityMiddleCol = entityMiddleWorldX / gp.tileSize;
		//int entityMiddleRow = entityMiddleWorldY / gp.tileSize;
		List<Integer> ColRow=new ArrayList<Integer>();
		ColRow.add(entityMiddleWorldX);
		ColRow.add(entityMiddleWorldY);
		//ColRow.removeAll(ColRow);
		return ColRow;
	}

	public void checkTile(Entity entity, double speed) {
		entity.collisionOn = false;
		int entityLeftWorldX = entity.worldX + entity.hitBox.x;
		int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
		int entityTopWorldY = entity.worldY + entity.hitBox.y;
		int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = (entityTopWorldY / gp.tileSize);
		int entityBottomRow = entityBottomWorldY / gp.tileSize;

		int tileNum1, tileNum2;

		switch (entity.direction) {

		case "up":
			entityTopRow = (int) ((entityTopWorldY - speed) / gp.tileSize);

			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];

			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;
			}
			
			break;
		case "down":
			entityBottomRow = (int) ((entityBottomWorldY + speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;

			}

			break;
		case "left":
			entityLeftCol = (int) ((entityLeftWorldX - speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];

			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;

			}
	
		

			break;
		case "right":
			entityRightCol = (int) ((entityRightWorldX + speed) / gp.tileSize);
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];

			if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
				entity.collisionOn = true;

			}

			break;

		}
	}

	public int checkObject(Entity entity, boolean player) {

		int index = 999;

		for (int i = 0; i < gp.obj.length; i++) {

			if (gp.obj[i] != null) {

				// GET ENTITY HITPOX POSITION
				entity.hitBox.x = entity.worldX + entity.hitBox.x;
				entity.hitBox.y = entity.worldY + entity.hitBox.y;
				// GET OBJECT HITBOX POSITION
				gp.obj[i].hitBox.x = gp.obj[i].worldX + gp.obj[i].hitBox.x;
				gp.obj[i].hitBox.y = gp.obj[i].worldY + gp.obj[i].hitBox.y;

				switch (entity.direction) {
				case "up":
					entity.hitBox.y -= entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "down":
					entity.hitBox.y += entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "left":
					entity.hitBox.x -= entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				case "right":
					entity.hitBox.x += entity.speed;
					if (entity.hitBox.intersects(gp.obj[i].hitBox)) {
						if (gp.obj[i].collision) {
							entity.collisionOn = true;
						}
						if (player) {
							index = i;
						}
					}
					break;
				}

				entity.hitBox.x = entity.hitBoxDefaultX;

				entity.hitBox.y = entity.hitBoxDefaultY;
				gp.obj[i].hitBox.x = gp.obj[i].hitBoxDefaultX;
				gp.obj[i].hitBox.y = gp.obj[i].hitBoxDefaultY;

			}
		}

		return index;
	}
	// check npc/enemy collision
	public int checkEntity(Entity entity, Entity[] target, double speed) {

		int index = 999;

		for (int i = 0; i < target.length; i++) {

			if (target[i] != null) {

				// GET ENTITY HITPOX POSITION
				entity.hitBox.x = entity.worldX + entity.hitBox.x;
				entity.hitBox.y = entity.worldY + entity.hitBox.y;
				// GET OBJECT HITBOX POSITION
				target[i].hitBox.x = target[i].worldX + target[i].hitBox.x;
				target[i].hitBox.y = target[i].worldY + target[i].hitBox.y;

				switch (entity.direction) {
				case "up":
					entity.hitBox.y -= speed;
					if (entity.hitBox.intersects(target[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					break;
				case "down":
					entity.hitBox.y += speed;
					if (entity.hitBox.intersects(target[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					break;
				case "left":
					entity.hitBox.x -= speed;
					if (entity.hitBox.intersects(target[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					break;
				case "right":
					entity.hitBox.x += speed;
					if (entity.hitBox.intersects(target[i].hitBox)) {
							entity.collisionOn = true;
							index = i;
					}
					break;
				}

				entity.hitBox.x = entity.hitBoxDefaultX;

				entity.hitBox.y = entity.hitBoxDefaultY;
				target[i].hitBox.x = target[i].hitBoxDefaultX;
				target[i].hitBox.y = target[i].hitBoxDefaultY;

			}
		}

		return index;
	}
	//May potentially result in errors if entity speed starts 
	//being affected by tiles, will fix if that does become the case
	public void checkPlayer(Entity entity) {
		// GET ENTITY HITPOX POSITION
		entity.hitBox.x = entity.worldX + entity.hitBox.x;
		entity.hitBox.y = entity.worldY + entity.hitBox.y;
		// GET OBJECT HITBOX POSITION
		gp.player.hitBox.x = gp.player.worldX + gp.player.hitBox.x;
		gp.player.hitBox.y = gp.player.worldY + gp.player.hitBox.y;

		switch (entity.direction) {
		case "up":
			entity.hitBox.y -= entity.speed;
			if (entity.hitBox.intersects(gp.player.hitBox)) {
					entity.collisionOn = true;
			}
			break;
		case "down":
			entity.hitBox.y += entity.speed;
			if (entity.hitBox.intersects(gp.player.hitBox)) {
					entity.collisionOn = true;
			}
			break;
		case "left":
			entity.hitBox.x -= entity.speed;
			if (entity.hitBox.intersects(gp.player.hitBox)) {
					entity.collisionOn = true;
			}
			break;
		case "right":
			entity.hitBox.x += entity.speed;
			if (entity.hitBox.intersects(gp.player.hitBox)) {
					entity.collisionOn = true;
			}
			break;
		}

		entity.hitBox.x = entity.hitBoxDefaultX;

		entity.hitBox.y = entity.hitBoxDefaultY;
		gp.player.hitBox.x = gp.player.hitBoxDefaultX;
		gp.player.hitBox.y = gp.player.hitBoxDefaultY;
	}
}
