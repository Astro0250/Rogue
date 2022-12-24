package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class EnemyTemp extends Item {
	public EnemyTemp() {
		
		name = "Enemy";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/enemy1.png"));
					
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
