package item;

import java.io.IOException;

import javax.imageio.ImageIO;

public class GateHor extends Item{

	public GateHor() {
		
		name = "GateHor";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/items/GateHor.png"));
			image2 = ImageIO.read(getClass().getResourceAsStream("/items/GateHorGap.png")); 
					
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		collision = true;
		
		hitBox.x = 0;
		hitBox.width = 144;
		sizeX = 144;
	}
}
