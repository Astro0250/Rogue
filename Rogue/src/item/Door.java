package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Door extends Item{

	public Door() {
		
		name = "Door";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/Door.png"));
					
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
		
		hitBox.x = 8;
		hitBox.width = 30;
	}
}
