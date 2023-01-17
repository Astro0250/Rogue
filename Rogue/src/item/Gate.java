package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Gate extends Item{

	public Gate() {
		
		name = "Gate";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/Gate.png"));
					
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
		
		hitBox.x = 0;
		hitBox.width = 48;
	}
}
