package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class SpeedPotion extends Item{
	public SpeedPotion() {
		
		name = "SpeedPotion";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/SpeedPotion.png"));
					
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
