package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class GateVertRL extends Item{

	public GateVertRL() {
		
		name = "GateVertRL";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/GateMidVertRL.png"));
					
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
		
		hitBox.x = 0;
		hitBox.width = 48;
	}
}
